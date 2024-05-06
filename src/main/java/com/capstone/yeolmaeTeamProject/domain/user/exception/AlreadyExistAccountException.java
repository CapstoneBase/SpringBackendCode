package com.capstone.yeolmaeTeamProject.domain.user.exception;

public class AlreadyExistAccountException extends RuntimeException {

    public AlreadyExistAccountException(String s) {
        super(s);
    }
}
