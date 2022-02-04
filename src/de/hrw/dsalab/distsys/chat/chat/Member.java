package de.hrw.dsalab.distsys.chat.chat;

public class Member implements NetworkListener{

    private static int anzahlMember;
    private static Chat[] array;

    private String nick;

    public Member(String nick){

        this.nick = nick;

    }

    public Member(int anzahlMember){

        this.anzahlMember = anzahlMember;

        Chat[] array = new Chat[anzahlMember];

        for(int i = 0; i < anzahlMember; i++) {
            array [i] = new Chat();
        }

        this.array = array;

    }


    @Override
    public void sendMessage(String msg){

        for (int i = 0; i < anzahlMember; i++){
            Chat member = array [i];
            if (!array [i].getNick().equals(this.nick)) {
                member.send(msg, nick);
            }
        }

    }

}
