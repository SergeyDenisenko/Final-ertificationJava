package Domain;

public class Encoder {

    public String encrypt(String text, int key) {
        char[] charArray = text.toCharArray();
        for (int i=0; i<charArray.length; i++) {
            charArray[i] += key;
        }
        return new String(charArray);
    }

    public String decrypt(String text, int key) {
        char[] charArray = text.toCharArray();
        for (int i=0; i<charArray.length; i++) {
            charArray[i] -= key;
        }
        return new String(charArray);
    }
}
