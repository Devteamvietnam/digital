package com.devteam.module.http;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.devteam.module.common.ClientInfo;

@Service
public class ClientSessionService {
  private Map<String, ClientSession> sessions = new ConcurrentHashMap<>();

  public boolean hasSession(String sessionId) { return sessions.containsKey(sessionId); }

  public ClientSession getSession(String sessionId) { return sessions.get(sessionId); }

  public ClientSession getSession(ClientInfo client) { return sessions.get(client.getSessionId()); }

  public void addSession(ClientSession container) {
    sessions.put(container.getSessionId(), container);
  }

  public ClientSession removeSession(String sessionId) {
    return sessions.remove(sessionId);
  }
}
