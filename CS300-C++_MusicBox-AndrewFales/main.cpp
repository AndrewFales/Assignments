#include "LinkedList.h"
#include "MusicBox.h"
#include <string>
#include <iostream>
using namespace std;
/*

    Melody Links music box for a user to make a playlist of songs, has generic playlist functionality
    to add, remove, go to next/prev song, display playlist and search playlist.

    Written by Andrew Fales 11/2/2023

*/
int main(){
    //Initialize MusicBox object.
    MusicBox mb;
    //Display option menu.
    cout<<"Welcome to the MelodyLinks!"<<endl;
    cout<<" "<<endl;
    cout<<"Menu: "<<endl;
    cout<<"1. Add a song to the playist"<<endl;
    cout<<"2. Remove song from the playlist"<<endl;
    cout<<"3. Play the next song."<<endl;
    cout<<"4. Play the previous song"<<endl;
    cout<<"5. Display the current song"<<endl;
    cout<<"6. Display the entire playlist with durations"<<endl;
    cout<<"7. Search for a song"<<endl;
    cout<<"8. Exit"<<endl;
    cout<<" "<<endl;

    //Variables to use in program run.
    bool run = true;
    int x;
    string title;
    int duration;
    //Loop to execute while user has not exited the program.
    while(run){
        cout<<"Choose an option:";
        int x = 0;
        cin >> x;
        //Checking to see if user input was a valid integer
        if(!cin >> x){
            cout<<" "<<endl;
            cout<<"Please enter a number."<<endl;
            cout<<" "<<endl;
            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
        }
        else{
            //Switch to handle user input. Only allows for valid user input.
            switch(x){

                //Gets input from user to add a song into the playlist.
                case 1:
                    cout<<" "<<endl;
                    cout<<"Enter song title:";
                    cin.ignore();
                    getline(cin, title);
                    cout<<"Enter song duration(in seconds):"<<endl;
                    cin >> duration;
                    if(!cin >> duration){ //Checking to see if input is a valid integer
                        cout<<"Input was not a number, please try adding a song again."<<endl;
                        cout<<" "<<endl;
                        cin.clear();
                        cin.ignore(numeric_limits<streamsize>::max(), '\n');
                        break;
                    }
                    mb.addSong(title, duration);
                    cout<<title<<" successfully added to the playlist."<<endl;
                    cout<<" "<<endl;
                    break;

                //Gets input from the user on the title to delete from the playlist
                case 2:
                    if(!mb.isEmpty()){ //Checking to see if the playlist is empty or not.
                        cout<<" "<<endl;
                        cout<<"Enter song title to remove:"<<endl;
                        cin.ignore();
                        getline(cin, title);
                        if(mb.removeSong(title)){
                            cout<<title<<" removed from playlist."<<endl;
                            cout<<" "<<endl;
                            break;
                        }
                        else{
                            cout<<"Could not find song to remove"<<endl;
                            cout<<" "<<endl;
                            break;
                        }
                    }
                    else{//If the playlist is empty
                        cout<<"There are no songs in the playlist."<<endl;
                        cout<<" "<<endl;
                        break;
                    }

                //Advancing to the next song, will loop back to the front if at end of the playlist.
                case 3:
                    if(mb.isEmpty()){
                        cout<<"There are no songs in the playlist."<<endl;
                        cout<<" "<<endl;
                        break;
                    }
                    if(mb.playNext()){
                        cout<<" "<<endl;
                        mb.displayCurrentSong();
                        cout<<" "<<endl;
                        break;
                    }

                //Moving the playlist to the previous song, will loop to the end if at the start of the playlist.
                case 4:
                    if(mb.isEmpty()){
                        cout<<"There are no songs in the playlist."<<endl;
                        cout<<" "<<endl;
                        break;
                    }
                    if(mb.playPrevious()){
                        cout<<" "<<endl;
                        mb.displayCurrentSong();
                        cout<<" "<<endl;
                        break;
                    }

                //Diplays current song
                case 5:
                    if(!mb.isEmpty()){
                        cout<<" "<<endl;
                        mb.displayCurrentSong();
                        cout<<" "<<endl;
                        break;
                    }
                    else{
                        cout<<"There are no songs in the playlist."<<endl;
                        cout<<" "<<endl;
                        break;
                    }

                //Displays the entire playlist
                case 6:
                    if(!mb.isEmpty()){
                        cout<<" "<<endl;
                        cout<<"Playlist: "<<endl;
                        mb.displayPlaylist();
                        cout<<" "<<endl;
                        break;
                    }
                    else{
                        cout<<"There are no songs in the playlist."<<endl;
                        cout<<" "<<endl;
                        break;
                    }

                //Searches for specific song by title.
                case 7:
                    if(!mb.isEmpty()){
                        cout<<" "<<endl;
                        cout<<"Enter song title to search for: "<<endl;
                        cin.ignore();
                        getline(cin, title);
                        if(mb.searchSong(title)){
                            cout<<"Song "<<title<<" was found in the playlist!"<<endl;
                            cout<<" "<<endl;
                        }
                        else{
                            cout<<"Song "<<title<<" was not found in the playlist."<<endl;
                            cout<<" "<<endl;
                        }
                        break;
                    }
                    else{
                        cout<<"There are no songs in the playlist."<<endl;
                        cout<<" "<<endl;
                        break;
                    }
                
                //Exit
                case 8:
                    run = false;
                    break;

                default:
                    cout<<" "<<endl;
                    cout<<"Option not available, please try again."<<endl;
                    cout<<" "<<endl;
                    break;
            }
        }
    }
    cout<<" "<<endl;
    cout<<"Thank you for using the MelodyLinks!"<<endl;
    return 0;
}