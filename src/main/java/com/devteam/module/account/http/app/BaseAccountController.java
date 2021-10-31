package com.devteam.module.account.http.app;

import com.devteam.common.ClientInfo;
import com.devteam.core.http.ClientSession;
import com.devteam.core.http.rest.v1.BaseController;
import com.devteam.module.account.security.AccountAclModel;
import com.devteam.module.account.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

public class BaseAccountController extends BaseController {
    final static Logger logger = LoggerFactory.getLogger(BaseAccountController.class);

    @Autowired
    protected AccountService accountService;

    protected BaseAccountController(String module, String service) {
        super(module, service);
    }

    protected AccountAclModel doChange(HttpSession session, AccountAclModel accountAclModel) throws Exception {
        ClientSession clientSession = getAuthorizedClientSession(session);
            clientSession.setBean(AccountAclModel.class, accountAclModel);
            return accountAclModel;
    }

    protected ClientContext getClientContext(HttpSession session) {
        ClientSession clientSession = getAuthorizedClientSession(session);
        ClientInfo client = clientSession.getClientInfo();
        AccountAclModel context = clientSession.getBean(AccountAclModel.class);
        return new ClientContext(client, context);
    }

    @AllArgsConstructor
    @Getter
    static public class ClientContext {
        ClientInfo clientInfo;
        AccountAclModel accountContext;
    }
}
