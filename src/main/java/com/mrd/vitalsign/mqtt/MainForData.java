/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrd.vitalsign.mqtt;

/**
 *
 * @author Admin
 */
public class MainForData {

    public static void main(String[] args) {
        new Thread(new PublisherSpo2()).start();
        new Thread(new PublisherBP()).start();
        new Thread(new PublisherTemp()).start();
    }
}
