#include "HashMap.h"
#include "Artist.h"
#include "User.h"
#include "Recommender.h"
#include <string>
#include <iostream>
using namespace std;


int main(){

    Recommender recommender("artists.dat", "user_artists.dat", "user_friends.dat");
    recommender.listFriends(2);
    cout << " " << endl;
    recommender.listArtists(2, 4);
    cout << " " << endl;
    recommender.commonFriends(2, 124);
    cout << " " << endl;
    recommender.recommend(2, 10);
    return 0;
}