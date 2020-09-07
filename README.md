## Game Of Life

A Java implementation of Conway's Game Of Life.
An infinite two-dimensional grid inhabited by 'cells'.
Each cell interacts with up to eight neighbours, which are the cells that are
horizontally, vertically, or diagonally adjacent.
From an initial seed grid the game "evolves" one iteration at a time. An iteration
applies rules to the grid to determine its next state.

Rules of the Game Of Life:
* 1 - No interactions - When there are no live cells
Then on the next generation there are still no live cells
* 2 - Underpopulation - When a live cell has fewer than two neighbours
Then this cell dies
* 3 - Overcrowding - When a live cell has more than three neighbours
Then this cell dies
* 4 - Survival - When a live cell has two or three neighbours
Then this cell stays alive
* 5 - Creation of Life - When an empty position has exactly three neighbouring cells
Then a cell is created in this position

A game of life with a starting state of no live cells: after one generation the next state also contains no live cells.


### Installing

Download the zip file and extract it.


## Built With

Java Eclipse

## Screenshots and Images

Generation 1 - Alive (blue) and dead (white) cells on start
![Generation1](https://raw.githubusercontent.com/ChloeLS/Game-Of-Life/master/Gen1.jpg)


Generation 2 - Alive (blue) and dead (white) cells after 1 generation 
 ![Generation2](https://raw.githubusercontent.com/ChloeLS/Game-Of-Life/master/Gen2.jpg)


## Authors

* **Chloe Skillman** - [Github](https://github.com/ChloeLS)
                                         [LinkedIn](https://www.linkedin.com/in/chloe-skillman-b80941183/)


## Acknowledgments
Useful sources which helped me a lot 
* https://stackoverflow.com/questions/20292418/manipulating-the-pixels-within-a-bufferedimage-through-an-array
