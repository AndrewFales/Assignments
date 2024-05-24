#ifndef _RECOMMENDER
#define _RECOMMENDER
#include "HashMap.h"
#include "Artist.h"
#include "User.h"
#include <string>
#include <fstream>
#include <sstream>
#include <unordered_set>
using namespace std;


class Recommender{
    private:
        HashMap<int, User, 5000> users;
        HashMap<int, Artist, 20000> artists;

        // Loads artists from a given file
        void loadArtists(const string& fileName) {
            // Open the file
            ifstream file(fileName);
            if (!file.is_open()) {
                cerr << "Error opening file: " << fileName << endl;
                return;
            }

            // Read artist data line by line
            string line;
            while (getline(file, line)) {
                // Extract artist ID and name
                stringstream ss(line);
                int id;
                string name;
                if (ss >> id) {
                    getline(ss, name);
                    // Create and insert artist object
                    Artist artist(id, name);
                    artists.insert(id, artist);
                } 
                else {
                    continue;
                }
            }

            // Close the file
            file.close();
        }

        void loadUserArtists(const string& fileName) {
            // Open the file
            ifstream file(fileName);
            if (!file.is_open()) {
                cerr << "Error opening file: " << fileName << endl;
                return;
            }

            // Read user artist associations line by line
            string line;
            while (getline(file, line)) {
                // Extract userID and artistID
                stringstream ss(line);
                int userID, artistID;
                if (ss >> userID >> artistID) {
                    // Create and insert user object
                    if(!users.contains(userID)){
                        User user(userID);
                        users.insert(userID, user);
                    }

                    // Check if artist exists
                    if (!artists.contains(artistID)) {
                        Artist artist(artistID, "");
                        artists.insert(artistID, artist);
                    }

                    // Add artist to user's artist list
                    users[userID].addArtist(artistID);
                } else {
                    continue;
                }
            }

            // Close the file
            file.close();
        
        }

        void loadUserFriends(const string& fileName) {

            // Open the file
            ifstream file(fileName);
            if (!file.is_open()) {
                cerr << "Error opening file: " << fileName << endl;
                return;
            }

            // Read user artist associations line by line
            string line;
            while (getline(file, line)) {
                // Extract userID and artistID
                stringstream ss(line);
                int userID, friendID;
                if (ss >> userID >> friendID) {
                    // Create and insert user object
                    if(!users.contains(userID)){
                        User user(userID);
                        users.insert(userID, user);
                    }

                    // Check if friend exists
                    if (!users.contains(friendID)) {
                        User user(userID);
                        users.insert(userID, user);
                    }

                    // Add artist to user's artist list
                    users[userID].addFriend(friendID);
                } else {
                    continue;
                }
            }

            // Close the file
            file.close();
        }


    public:
        //Constructor for recommender class
        Recommender(string artistFile, const string& userArtistsFile, const string& userFriendsFile){
            //Loads all the files into the hashmaps
            
            loadArtists(artistFile);

            loadUserArtists(userArtistsFile);

            loadUserFriends(userFriendsFile);

        }

        //Method to list all the friends of a given user
        void listFriends(int userID){
            if(users.contains(userID)){
                //Get the users friends data
                User& user = users[userID];
                const unordered_set<int>& friendsIDs = user.getFriendIDs();

                //if the user has friends, print out all the friends.
                if(!friendsIDs.empty()){
                    cout<<"Friends of User " << userID << ":\n";
                    for(int friendID : friendsIDs){
                        cout << friendID << "\n";
                    }
                }
                else{
                    cout<< "User " << userID << " has no friends.\n";
                }

            }
            else{
                cout << "User " << userID << " not found.\n";
            }
        }

        //Method to get common friends between two users
        void commonFriends(int user1ID, int user2ID){

            if(users.contains(user1ID) && users.contains(user2ID)){
                User& user1 = users[user1ID];
                User& user2 = users[user2ID];

                //Retrieve each users friends data
                unordered_set<int>& friends1 = user1.getFriendIDs();
                unordered_set<int>& friends2 = user2.getFriendIDs();
                
                //Retrieve common friends between each user
                unordered_set<int> commonFriends;
                for(int friendID : friends1){
                    if(friends2.find(friendID) != friends2.end()){
                        commonFriends.insert(friendID);
                    }
                }

                //If they have common friends print them out
                if(!commonFriends.empty()){
                    cout <<"Common friends between user " << user1ID << " and user " << user2ID << ":";
                    cout<< " ";
                    for(int friendID : commonFriends){
                        cout << friendID << " ";
                    }
                }
                else{
                    cout << "No common friends between user " << user1ID << " and user: " << user2ID << ":\n";
                }
            }
            else{
                cout << "One or both of the given users could not be found. \n";
            }
        }

        //Method to list common artists between two users
        void listArtists(int user1ID, int user2ID){
            if(users.contains(user1ID) && users.contains(user2ID)){
                User& user1 = users[user1ID];
                User& user2 = users[user2ID];
                //Get artist info for each user
                unordered_set<int>& artists1 = user1.getArtistIDs();
                unordered_set<int>& artists2 = user2.getArtistIDs();

                //Retrieve common artists and store them in set.
                unordered_set<int> commonArtists;
                for(int artistID : artists1){
                    if(artists2.find(artistID) != artists2.end()){
                        commonArtists.insert(artistID);
                    }
                }

                //If common artists exist, print them out
                if(!commonArtists.empty()){
                    cout << "Artists listened to by both user " << user1ID << " and user " << user2ID << ":\n";
                    for(int artistID : commonArtists){
                        cout << artists[artistID].getName() << "\n";
                    }
                }
                else{
                    cout << "No common artists between user " << user1ID << " and user " <<  user2ID << ":\n";
                }
                }
            else{
                cout << "One or both of the given users could not be found. \n";
            }
        }
        
        //Method to recommend top songs to a give user
        void recommend(int userID, int top) {
            if (users.contains(userID)) {
                //Grab the users friends and artist set data
                User& user = users[userID];
                const unordered_set<int>& userFriends = user.getFriendIDs();
                const unordered_set<int>& userArtists = user.getArtistIDs();
                
                //For storing the recommended songs
                HashMap<int, pair<string, int>, 1000> recommendations;

                // Count the listen count for the user
                for (int artistID : userArtists) {
                    const Artist& artist = artists[artistID];
                    string artistName = artist.getName();
                    recommendations[artistID].first = artistName;
                    recommendations[artistID].second += 1;
                }

                // Count the listen count for each friend
                for (int friendID : userFriends) {
                    if (users.contains(friendID)) {
                        User& friendUser = users[friendID];
                        const unordered_set<int>& friendArtistsSet = friendUser.getArtistIDs();

                        for (int artistID : friendArtistsSet) {
                            // Increment the listen count for the artist
                            recommendations[artistID].first = artists[artistID].getName();
                            recommendations[artistID].second += 1;
                        }
                    }
                }

                // Bubble sort the vector based on the listen count in descending order
                vector<pair<int, pair<string, int>>> sortedRecommendations = recommendations.sortHashMapByValues();

                for (size_t i = 0; i < sortedRecommendations.size() - 1; i++) {
                    for (size_t j = 0; j < sortedRecommendations.size() - i - 1; j++) {
                        if (sortedRecommendations[j].second.second < sortedRecommendations[j + 1].second.second) {
                            swap(sortedRecommendations[j], sortedRecommendations[j + 1]);
                        }
                    }
                }

                // Print the top recommendations
                cout << "Recommendations for User " << userID << ":" << endl;
                for (int i = 0; i < min(top, static_cast<int>(sortedRecommendations.size())); ++i) {
                    cout << "Artist ID: " << sortedRecommendations[i].first
                        << ", Name: " << sortedRecommendations[i].second.first
                        << ", Listen Count: " << sortedRecommendations[i].second.second << endl;
                }
            } else {
                cout << "User " << userID << " not found." << endl;
            }
        }

};


#endif