package com.batigobackend.batigo.Entity;

public enum TaskStatus {
    TODO, IN_PROGRESS, DONE;

    public boolean isValidTransition(TaskStatus newStatus) {
      return true;
    }
}