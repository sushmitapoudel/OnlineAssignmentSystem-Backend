package com.sushmita.onlineassignmentsystem.util;

import com.sushmita.onlineassignmentsystem.model.User;

public class NameUtil {

    public static String getFullName(User user){
        return user.getFirstName() + " "  + user.getLastName();
    }
}
