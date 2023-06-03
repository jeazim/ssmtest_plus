package com.atzhi.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.reflection.ArrayUtil;
import com.atzhi.pojo.Message;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.util.*;

@ServerEndpoint("/chatroom")
public class Socket {
    //    static Set<Session> set=new HashSet<Session>();
//    static List<String> userlist=new ArrayList<String>();
    static Map<Session,String> us=new HashMap<Session,String>();
    Map<String,String> map;
    private Message ms;
    @OnOpen
    public void onOpen(Session session) throws IOException {
        System.out.println("连接建立成功！！！");
        String msg= session.getQueryString();
        msg = URLDecoder.decode(msg, "utf-8");
        map=new HashMap<String,String>();
        if(msg.contains("&"))
        {
            String[] sts=msg.split("\\&");
            for (String str:sts)
            {
                String[] strs=str.split("=");
                map.put(strs[0],strs[1]);
            }
        }else {
            String[] strs=msg.split("=");
            map.put(strs[0],strs[1]);
        }
        us.put(session,map.get("username"));
//        userlist.add(map.get("username"));
        ms=new Message();
        ms.setType("s");
        ms.setMsgSender(map.get("username"));
        ms.setMsgDate(new Date());
        ms.setUserList(new ArrayList<String>(us.values()));
        ms.setMsgInfo(map.get("username")+"上线了!");
        String jsonstr = JSONObject.toJSONString(ms);
//        set.add(session);
        this.bord(us.keySet(), jsonstr);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
//        userlist.remove(map.get("username"));
        us.remove(session);
        ms=new Message();
        ms.setType("s");
        ms.setMsgSender("system");
        ms.setMsgDate(new Date());
        ms.setUserList(new ArrayList<String>(us.values()));
        ms.setMsgInfo(map.get("username")+"已下线!");
        String jsonstr = JSONObject.toJSONString(ms);
//        set.remove(session);
        bord(us.keySet(), jsonstr);
        System.out.println("连接已关闭！！！");
    }

    @OnMessage
    public void onMessage(String message,Session session) throws IOException {
        System.out.println("信息接收！！！" + message);
        ms = new Message();
        ms.setType("p");
        ms.setMsgSender((String)this.map.get("username"));
        ms.setMsgDate(new Date());
        Session resession;
        boolean falg=false;
        if (message.startsWith("@")&&message.contains(":"))
        {
            String rename=message.substring(message.indexOf("@")+1,message.indexOf(":"));
            if (us.containsValue(rename))
            {
                Set<Session> s1=us.keySet();
                for (Session key:s1)
                {
                    String ne=us.get(key);
                    if (rename.equals(ne))
                    {
                        resession=key;
                        falg=true;
                        message=message.substring(message.indexOf(":")+1);
                        ms.setMsgInfo(message);
                        ms.setMsgReceiver(rename);
                        String jsonstr = JSONObject.toJSONString(ms);
                        Set<Session> set=new HashSet<Session>();
                        set.add(session);
                        set.add(resession);
                        this.bord(set, jsonstr);
                        break;
                    }
                }
            }
        }
        if (!falg) {
            ms.setMsgInfo(message);
            String jsonstr = JSONObject.toJSONString(ms);
            this.bord(us.keySet(), jsonstr);
        }
    }
    byte[] bytes=null;

    @OnMessage
    public void onMessage(byte[] input,Session session,boolean flag) throws IOException {
        if(!flag)
        {
            bytes= ArrayUtils.addAll(bytes, input);
        }else {
            bytes= ArrayUtils.addAll(bytes, input);
            ByteBuffer wrap = ByteBuffer.wrap(bytes);
            ms = new Message();
            ms.setType("img");
            ms.setMsgSender(map.get("username"));
            ms.setMsgDate(new Date());
            String jsonstr = JSONObject.toJSONString(ms);
            this.bord(us.keySet(), jsonstr);
            bord(us.keySet(),wrap);
            bytes=null;
        }
    }
    @OnError
    public void onError(Throwable t) throws Throwable {
        System.out.println("系统异常！！！");
    }
    public void bord(Set<Session> set,String message) throws IOException {
        for(Session s:set)
            s.getBasicRemote().sendText(message);
    }
    public void bord(Set<Session>set, ByteBuffer img) throws IOException {
        for(Session s:set){
            s.getBasicRemote().sendBinary(img);
        }
    }

}