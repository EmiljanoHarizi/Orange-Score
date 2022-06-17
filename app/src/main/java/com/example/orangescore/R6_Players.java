package com.example.orangescore;

public class R6_Players {
    String player_name;
    int pnt;
    int rib;
    String pos;
    int ep;
    int ap;
    int fl;
    int kl;
    int ko;

    private R6_Players() {}

    private R6_Players(String player_name, int pnt, int rib, String pos, int ep, int ap, int fl, int kl, int ko) {
        this.player_name = player_name;
        this.pnt = pnt;
        this.rib = rib;
        this.pos = pos;
        this.ep = ep;
        this.ap = ap;
        this.fl = fl;
        this.kl = kl;
        this.ko = ko;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public int getPnt() {
        return pnt;
    }

    public void setPnt(int pnt) {
        this.pnt = pnt;
    }

    public int getRib() {
        return rib;
    }

    public void setRib(int rib) {
        this.rib = rib;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public int getEp() {
        return ep;
    }

    public void setEp(int ep) {
        this.ep = ep;
    }

    public int getAp() {
        return ap;
    }

    public void setAp(int ap) {
        this.ap = ap;
    }

    public int getFl() {
        return fl;
    }

    public void setFl(int fl) {
        this.fl = fl;
    }

    public int getKl() {
        return kl;
    }

    public void setKl(int kl) {
        this.kl = kl;
    }

    public int getKo() {
        return ko;
    }

    public void setKo(int ko) {
        this.ko = ko;
    }
}

