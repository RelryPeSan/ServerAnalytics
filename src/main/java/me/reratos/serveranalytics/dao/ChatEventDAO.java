package me.reratos.serveranalytics.dao;

import me.reratos.serveranalytics.model.ChatEventModel;

public class ChatEventDAO extends IdGenericDAO<ChatEventModel, Long> {

    public ChatEventDAO() {
        super(ChatEventModel.class);
    }

}
