#ifndef MUSIC_BOX
#define MUSIC_BOX
#include "LinkedList.h"
#include <iostream>
using namespace std;
/*

    Music Box class to build a playlist
    Internally uses a linked list to store songs.

    Andrew Fales
*/

//Defining what a song is. A song has a title, and a duration in seconds.
struct Song{
    string title;
    int duration;
    bool operator==( const Song& s){ //Defining comparison operator
        return this->title == s.title;
    }
};

class MusicBox{
    //LinkedList to store songs and a position integer to track where the current song is in the playlist.
    private:
        LinkedList<Song> playlist;
        int position = 0;
    public:
        //Function to add a new song to the end of linked list, the UI will allow any string to be the song
        //but only allows a valid integer to be the duration.
        void addSong(string title, int duration){
            struct Song newSong;
            newSong.title = title;
            newSong.duration = duration;
            playlist.push_back(newSong);
        }

        //Function to display all the songs stored in the linked list, starting with the first song.
        void displayPlaylist(){
            for(int i = 0; i < playlist.getSize(); i++){
                cout<<playlist.at(i).title<<" - "<<playlist.at(i).duration<<" seconds"<<endl;
            }
        }

        //Function to remove a song from the linked list. Returns true if successfully found and removed.
        bool removeSong(string title){
            for(int i = 0; i < playlist.getSize(); i++){
                if(playlist.at(i).title == title){
                    playlist.remove(playlist.at(i));
                    return true;
                }
            }
            return false;
        }

        //Function to search the linked list for a song. Returns true if successfully found.   
        bool searchSong(string title){
            for(int i = 0; i < playlist.getSize(); i++){
                if(playlist.at(i).title == title){
                    return true;
                }
            }
            return false;
        }

        //Function to move the playlist foward one spot. If it reached the end of the playlist, it loops back to the start.
        bool playNext(){
            if(!playlist.isEmpty()){
                if(position == playlist.getSize() - 1){
                    position = 0;
                }
                else{
                    position++;
                }
                return true;
            }
            return false;
        }

        //Function to move the playlist foward one spot. If it reached the start of the playlist, it loops back to the end.
        bool playPrevious(){
            if(!playlist.isEmpty()){
                if(position == 0)
                    position = playlist.getSize() - 1;
                else{
                    position--;
                }
                return true;
            }
            return false;
        }

        //Function to display the current song in the playlist.
        void displayCurrentSong(){
            cout<<"Now playing: "<<currentSong().title<<" Duration: "<<currentSong().duration<<" seconds."<<endl;
        }

        bool isEmpty(){
            return playlist.isEmpty();
        }

        //Function to return the current song in the playlist.
        Song currentSong(){
            return playlist.at(position);
        }
};

#endif