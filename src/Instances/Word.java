package Instances;

import java.util.ArrayList;
import java.util.List;

public abstract class Word {
    private List<String> words = new ArrayList<>();

    public List<String> getWords(){
        return this.words;
    }

    public void setWords(String word){
        words.add(word);
    }

    @Override
    public String toString(){
        for (String word : words) {
            System.out.println(word);
        }
        return ""+words.size();
    }
}
