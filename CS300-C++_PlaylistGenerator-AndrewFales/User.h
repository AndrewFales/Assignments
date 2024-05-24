#ifndef _USER
#define _USER
#include <unordered_set>

using namespace std;

class User{
    private:
        int id;
        unordered_set<int> friendIDs;
        unordered_set<int> artistIDs;
    public:
        User(){}
        User(int _id):id(_id){}

        void addFriend(int friendID){
            friendIDs.insert(friendID);
        }

        void addArtist(int artistID){
            artistIDs.insert(artistID);
        }
        
        const int getID() const{
            return this->id;
        }

        unordered_set<int>& getFriendIDs(){
            return friendIDs;
        }

        unordered_set<int>& getArtistIDs(){
            return artistIDs;
        }
};





#endif