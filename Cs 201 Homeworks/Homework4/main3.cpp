#include <iostream>
#include "AsteroidField.h"

using namespace std;

int main() {
    AsteroidField af("test3.txt");
    af.display();

    af.collide();
    af.display();

    af.displayAsteroidStatus(1);
    af.displayAsteroidStatus(2);
    af.displayAsteroidStatus(3);

    af.addAsteroid(4, 5, 'L');
    af.display();

    af.collide();
    af.display();

    af.displayAsteroidStatus(2);
    af.displayAsteroidStatus(3);
    af.displayAsteroidStatus(4);

    return 0;
}