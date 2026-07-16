package it.unipv.ingsfw.model;

/**
 * Classe che rappresenta un singolo slot orario (Tee Time) nel sistema.
 * Utilizzato per popolare la griglia del planning giornaliero visibile all'amministratore.
 *
 * @author Leah Appiah
 * @version 1.0
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