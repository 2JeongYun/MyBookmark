package com.github.neukrang.mybookmark.config;

public class TextConfig {

    public static String cantFindBookmarkMsg(Long id) {
        return "해당 북마크를 찾을 수 없습니다. ID:" + id;
    }

    public static String cantFindCategoryMsg(Long id) {
        return "해당 카테고리를 찾을 수 없습니다. ID:" + id;
    }

    public static String cantFindSectionMsg(Long id) {
        return "해당 섹션을 찾을 수 없습니다. ID:" + id;
    }

    public static String cantFindRegistrationIdMsg(String id) {
        return "해당 OAuth 서비스를 찾을 수 없습니다. ID:" + id;
    }

    public static String cantFindUserMsg(Long id) {
        return "해당 유저를 찾을 수 없습니다. ID:" + id;
    }
}
