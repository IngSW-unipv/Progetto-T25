package it.unipv.ingsfw.model;

/**
 * Classe per la visualizzazione dello slot del tee-time orario
 */

 public class TeeTimeSlot {

    private final String time;
    private final String status;
    private final String details;

 public TeeTimeSlot(String time, String status, String details) {

    this.time = time;
    this.status = status;
    this.details = details;

 }

 public String getTime() { return time; }

 public String getStatus() { return status; }

 public String getDetails() { return details; }

 }