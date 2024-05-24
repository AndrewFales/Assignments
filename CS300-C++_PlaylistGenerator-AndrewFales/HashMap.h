#ifndef HASH_MAP
#define HASH_MAP
#include <vector>
#include <list>

using namespace std;

// HashEntry struct to store key-value pairs
template<typename K, typename V>
struct HashEntry{
    V value;
    K key;

    HashEntry(K key, V value){
        this->value = value;
        this->key = key;
    }
    

    bool operator==(const HashEntry& x) const{
        return this->key == x->key;
    }
};

template<typename K, typename V, int TableSize>
class HashMap{
    private:
        vector<list<HashEntry<K,V>>> table;
    public:
        HashMap() : table(TableSize){}

        // Insert in the hashmap
        void insert(const K& key, const V& value){
            // size_t hashValue = hash<K>{}(key);
            // Find a place in the table for the new entry
            size_t index = key % TableSize;
            // Create and insert new entry
            HashEntry<K, V> newEntry(key, value);
            table[index].push_front(newEntry);
        }

        // Find a given key value pair, return the value and true if found
        bool get(const K& key, V& value) const{
            // size_t hashValue = hash<K>{}(key);
            size_t index = key % TableSize;
            // Find the entry in the table, and set the value equal to given value
            for(const auto& entry : table[index]){
                if(entry.key == key){
                    value = entry.value;
                    return true;
                }
            }
            return false;
        }

        // Remove a given key from the hashmap
        void remove(const K& key){
            // size_t hashValue = hash<K>{}(key);
            size_t index = key % TableSize;

            // Iterate through the table to find given key
            auto& entries = table[index];
            for(auto it = entries.begin(); it != entries.end(); it++){
                if(it->key == key){
                    entries.erase(it); //Remove from table
                    return; //exit the loop
                }
            }
        }

        // Check to see if key exists in the hashmap
        bool contains(const K& key) const{
            // size_t hashValue = hash<K>{}(key);
            size_t index = key % TableSize;
            
            // Iterate through the table to find a given key
            for(const auto& entry : table[index]){
                if(entry.key == key){ //Check each entry
                    return true;
                }
            }
            return false;
        } 
        
        // Sorts and returns the hashmap as a new vector
        vector<pair<K, V>> sortHashMapByValues() const{
            // Create new vector
            vector<pair<K, V>> sortedPairs;
            // Enter our current hashmap entries into the new vector;
            for(const auto& entries : table){
                for(const auto& entry : entries){
                    sortedPairs.push_back(make_pair(entry.key, entry.value));
                }
            }

            // Bubble sort
            for(size_t i = 0; i < sortedPairs.size() - 1; i++){
                for(size_t j = 0; j < sortedPairs.size() - i - 1; j++){
                    // Comparing pair values and swapping when necessary
                    if(sortedPairs[j].second < sortedPairs[j + 1].second){
                        swap(sortedPairs[j], sortedPairs[j+1]);
                    }
                }
            }

            return sortedPairs;
        }
        
        // Access operator to directly access a given key
        V& operator[](const K& key){
            // Get the index in the map from the given key
            // size_t hashValue = hash<K>{}(key);
            size_t index = key % TableSize;
            
            // Iterate through the table
            for(auto& entry : table[index]){
                if(entry.key == key){
                    return entry.value;
                }
            }

            HashEntry<K, V> newEntry(key, V{});
            table[index].push_front(newEntry);

            return table[index].front().value;
        }   
        
        // Utility functions used to iterate
        auto begin() const {return table.begin();};

        auto end() const {return table.end();};
        
};


#endif