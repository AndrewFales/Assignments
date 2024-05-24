#ifndef _ARTIST
#define _ARTIST
#include <string>
#include <iostream>

using namespace std;

class Artist{
    private:
        int id;
        string name;
    
    public:
        Artist(): id(0), name(""){}
        Artist(int _id, string _name):id(_id), name(_name){}

        void setID(int newID){
            id = newID;
        }

        void setName(string newName){
            name = newName;
        }

        int getID() const{
            return id;
        }

        string getName() const{
            return name;
        }
};




#endif