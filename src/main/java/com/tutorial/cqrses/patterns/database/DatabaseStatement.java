package com.tutorial.cqrses.patterns.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.tutorial.cqrses.patterns.es.events.Event;
import com.tutorial.cqrses.patterns.es.events.UserCreatedEvent;

public class DatabaseStatement {
    
    public static void insertEvent(Event event) throws SQLException {
        String sql = "INSERT INTO events (event_id, event_type, user_id, description) values (?,?,?,?)";
        String eventType = "", userId = "", description = "";
        if(event instanceof UserCreatedEvent) {
            eventType = "User Created";
            UserCreatedEvent ev = (UserCreatedEvent) event;
            userId = ev.getUserId();
            description = new Gson().toJson(ev);
        }
        PreparedStatement ps = 
            DatabaseConnection.getDBConnection().prepareStatement(sql);
        ps.setString(1, event.id.toString());
        ps.setString(2, eventType);
        ps.setString(3, userId);
        ps.setString(4, description);

        ps.execute();

        DatabaseConnection.getDBConnection().close();
    }

}
