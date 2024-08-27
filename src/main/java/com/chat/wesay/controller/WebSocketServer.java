package com.chat.wesay.controller;

import com.chat.wesay.pojo.GroupMember;
import com.chat.wesay.pojo.Message;
import com.chat.wesay.pojo.UnreadMessage;
import com.chat.wesay.service.impl.GroupMemberServiceImpl;
import com.chat.wesay.service.impl.MessageServiceImpl;
import com.chat.wesay.service.impl.UnreadMessageServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.ApplicationContext;
@Component
@ServerEndpoint("/api/websocket/{userId}")
public class WebSocketServer {

    private static MessageServiceImpl messageService;
    private static GroupMemberServiceImpl groupMemberService;
    private static UnreadMessageServiceImpl unreadMessageService;

    @Autowired
    public void setUnreadMessageService(UnreadMessageServiceImpl unreadMessageService){
        WebSocketServer.unreadMessageService=unreadMessageService;
    }


    @Autowired
    public void setMessageService(MessageServiceImpl messageService){
        WebSocketServer.messageService=messageService;
    }

    @Autowired
    public void setGroupMemberService(GroupMemberServiceImpl groupMemberService) {
        WebSocketServer.groupMemberService = groupMemberService;
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Map<Long, Session> onlineUsers = new ConcurrentHashMap<>();
    @OnOpen
    public void onOpen(@PathParam("userId") Long userId, Session session) {
        onlineUsers.put(userId, session);
    }

    @OnMessage
    public void onMessage(@PathParam("userId") Long userId, String message) throws IOException {
        Message messagePojo= objectMapper.readValue(message,Message.class);
        if(messagePojo.getChatType()==1){
            Session rs = onlineUsers.get(messagePojo.getReceiverId());
            messagePojo.setChatType(1);
            messagePojo.setIsRead(0);
            messageService.save(messagePojo);
            if(rs!=null){
                rs.getBasicRemote().sendText(message);
            }
        }else{
            List<GroupMember> members = groupMemberService.getMembersByGroupId(messagePojo.getReceiverId());
            messagePojo.setChatType(0);
            messagePojo.setIsRead(1);
            messageService.save(messagePojo);
            for(GroupMember gm:members){
                Session gs = onlineUsers.get(gm.getUserId());
                if(gs!=null&& !Objects.equals(gm.getUserId(), userId)){
                    gs.getBasicRemote().sendText(message);
                }else if (!Objects.equals(gm.getUserId(), userId)){
                    UnreadMessage un=new UnreadMessage();
                    un.setMessageId(messagePojo.getMessageId());
                    un.setGroupId(messagePojo.getReceiverId());
                    un.setUserId(gm.getUserId());
                    unreadMessageService.save(un);
                }
            }
        }
    }

    @OnClose
    public void onClose(@PathParam("userId") Long userId, Session session) {
        onlineUsers.remove(userId);
        System.out.println("Connection closed: " + session.getId());
    }
    @OnError
    public void onError(Throwable error) {
        System.out.println("onError:"+error.getMessage());
    }
}
