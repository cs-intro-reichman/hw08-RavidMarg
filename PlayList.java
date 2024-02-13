/** Represnts a list of musical tracks. The list has a maximum capacity (int),
 *  and an actual size (number of tracks in the list, an int). */
class PlayList {
    private Track[] tracks;  // Array of tracks (Track objects)   
    private int maxSize;     // Maximum number of tracks in the array
    private int size;        // Actual number of tracks in the array

    /** Constructs an empty play list with a maximum number of tracks. */ 
    public PlayList(int maxSize) {
        this.maxSize = maxSize;
        tracks = new Track[maxSize];
        size = 0;
    }

    /** Returns the maximum size of this play list. */ 
    public int getMaxSize() {
        return maxSize;
    }
    
    /** Returns the current number of tracks in this play list. */ 
    public int getSize() {
        return size;
    }

    /** Method to get a track by index */
    public Track getTrack(int index) {
        if (index >= 0 && index < size) {
            return tracks[index];
        } else {
            return null;
        }
    }
    
    /** Appends the given track to the end of this list. 
     *  If the list is full, does nothing and returns false.
     *  Otherwise, appends the track and returns true. */
    public boolean add(Track track) {
        boolean result = true;

        if (size < maxSize){
            tracks[size++] = track;
        }
        else {
            result = false;
        }
        return result;
    }

    /** Returns the data of this list, as a string. Each track appears in a separate line. */
    //// For an efficient implementation, use StringBuilder.
    public String toString() {
        StringBuilder str_builder = new StringBuilder();
        for (int i = 0; i < size; i++){
            str_builder.append(tracks[i].toString()).append("\n");
        }
        return str_builder.toString();
    }

    /** Removes the last track from this list. If the list is empty, does nothing. */
     public void removeLast() {
         if (size != 0){
             this.remove(size - 1);
         }
    }
    
    /** Returns the total duration (in seconds) of all the tracks in this list.*/
    public int totalDuration() {
        int sum = 0;
        for (int i = 0; i < size; i++){
            sum = sum + tracks[i].getDuration();
        }
        return sum;
    }

    /** Returns the index of the track with the given title in this list.
     *  If such a track is not found, returns -1. */
    public int indexOf(String title) {
        for (int i = 0; i < size; i++){
            if (tracks[i].getTitle().equals(title)){
                return i;
            }
        }
        return -1;
    }

    /** Inserts the given track in index i of this list. For example, if the list is
     *  (t5, t3, t1), then just after add(1,t4) the list becomes (t5, t4, t3, t1).
     *  If the list is the empty list (), then just after add(0,t3) it becomes (t3).
     *  If i is negative or greater than the size of this list, or if the list
     *  is full, does nothing and returns false. Otherwise, inserts the track and
     *  returns true. */
    public boolean add(int i, Track track) {
        boolean result = true;
        if((size == 0 && i == 0) || ((size < maxSize) && (i > 0 && i < maxSize))){
            for (int j = size ; j >= i; j--) {
                tracks[j+1] = tracks[j];
            }
            tracks[i] = track;
            size++;
        }
        else {
            result = false;
        }
        return false;
    }
     
    /** Removes the track in the given index from this list.
     *  If the list is empty, or the given index is negative or too big for this list, 
     *  does nothing and returns -1. */
    public void remove(int i) {
        if((size < 0) && (i >= 0) && (i <= maxSize)){
            for (int j = i; j < size - 1; j++) {
                tracks[j] = tracks[j+1];
            }
            size--;
        }
        else {
            // Should return -1 according to the description but the given skelaton + happy flow case returns none,
            // so in order to not change the given "void" setting we return none.
            return ;
        }
    }

    /** Removes the first track that has the given title from this list.
     *  If such a track is not found, or the list is empty, or the given index
     *  is negative or too big for this list, does nothing. */
    public void remove(String title) {
        int index_of_title = this.indexOf(title);

        if (index_of_title != -1){
            this.remove(index_of_title);
        }
    }

    /** Removes the first track from this list. If the list is empty, does nothing. */
    public void removeFirst() {
        if (size != 0){
            this.remove(0);
        }
    }
    
    /** Adds all the tracks in the other list to the end of this list. 
     *  If the total size of both lists is too large, does nothing. */
    //// An elegant and terribly inefficient implementation.
     public void add(PlayList other) {
         int other_size = other.getSize();
        if ((other_size + size) <= maxSize){
            for (int i = 0; i < other_size; i++){
                this.add(other.tracks[i]);
            }
        }
    }

    /** Returns the index in this list of the track that has the shortest duration,
     *  starting the search in location start. For example, if the durations are 
     *  7, 1, 6, 7, 5, 8, 7, then min(2) returns 4, since this the index of the 
     *  minimum value (5) when starting the search from index 2.  
     *  If start is negative or greater than size - 1, returns -1.
     */
    private int minIndex(int start) {
        int result = -1;
        if (start >= 0 || start < size) {
            int min_index = start;
            int min_duration = tracks[start].getDuration();
            for (int i = start + 1; i < size; i++) {
                if (tracks[i].getDuration() < min_duration) {
                    min_index = i;
                    min_duration = tracks[i].getDuration();
                }
            }
            result = min_index;
        }
        return result;
    }

    /** Returns the title of the shortest track in this list. 
     *  If the list is empty, returns null. */
    public String titleOfShortestTrack() {
        return tracks[minIndex(0)].getTitle();
    }

    /** Sorts this list by increasing duration order: Tracks with shorter
     *  durations will appear first. The sort is done in-place. In other words,
     *  rather than returning a new, sorted playlist, the method sorts
     *  the list on which it was called (this list). */
    public void sortedInPlace() {
        for(int i = 0; i < size; i++){
            int min_index = minIndex(i);
            Track tmp_track = tracks[i];
            tracks[i] = tracks[min_index];
            tracks[min_index]= tmp_track;

        }
    }
}
