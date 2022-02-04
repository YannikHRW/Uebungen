package de.hrw.dsalab.distsys.chat.xml;

public class Chat {

    private String ip;
    private String port;
    private String nickname;

    public Chat(String ip, String port, String nickname) {

        this.ip = ip;
        this.port = port;
        this.nickname = nickname;

    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String toString() {
        return "Dieser Chat ist " + this.ip.trim() + " " + this.port.trim() + " " + this.nickname.trim();
    }

}
