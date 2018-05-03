package com.yunbin.circuitbreaker;

/**
 * Created by cloud.huang on 18/5/3.
 */
public class SlidingWindow {
    int size;
    int[] counts;
    long lastTime = 0;
    
    public SlidingWindow(int size) {
        this.size = size;
        counts = new int[size];
    }
    
    public void add(long time) {
        clear(time);
        int index = (int) (time / size);
        counts[index] = counts[index] + 1;
        
        lastTime = time;
    }
    
    public int count(long time) {
        clear(time);
        int result = 0;
        for (int count : counts) {
            result += count;
        }
        return result;
    }
    
    private void clear(long time) {
        if (time < lastTime + size) {
            return;
        }
        for (int i = 0; i < size; i++) {
            counts[i] = 0;
        }
    }
    
    
}
