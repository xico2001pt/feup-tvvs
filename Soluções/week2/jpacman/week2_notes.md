# ‘Category-Partition’ and ‘Boundary Value Analysis’ - Week 2

## 1.1 squareAt in the nl.tudelft.jpacman.board.Board class.

### Function

```java
public Square squareAt(int x, int y)

Returns the square at the given x,y position. Precondition: The (x, y) coordinates are within the width and height of the board.

Parameters:
x - The x position (column) of the requested square.
y - The y position (row) of the requested square.

Returns:
The square at the given x,y position (never null).
```

### Category-Partition
We can partition the input space into these categories:
- For x:
    - C1: x < 0
    - C2: x >= 0 && x < width
    - C3: x >= width
- For y:
    - C4: y < 0
    - C5: y >= 0 && y < height
    - C6: y >= height
- For output:
    - C7: Square is null
    - C8: Square is not null and is not occupied
    - C9: Square is not null and is occupied

### Boundary Value Analysis
We can use the following values to test the function:
- For x:
    - x = -1 (C1)
    - x = 0 (C2)
    - x > 0 && x < width (C2)
    - x = width (C3)
    - x = width + 1 (C3)
- For y:
    - y = -1 (C4)
    - y = 0 (C5)
    - y > 0 && y < height (C5)
    - y = height (C6)
    - y = height + 1 (C6)
- For output:
    - Square is null (C7)
    - Square is not null an occupied (C8)
    - Square is not null and not occupied (C8)

### Test Cases
We can use the following test cases to test the function:
Given that width = 10 and height = 10:

1. x = -1, y = -1 (C1, C4) and Square object is null (C7)
2. x = 0, y = -1 (C2, C4) and Square object is null (C7)
3. x = width - 1, y = -1 (C2, C4) and Square object is null (C7)
4. x = width, y = -1 (C3, C4) and Square object is null (C7)
5. x = width + 1, y = -1 (C3, C4) and Square object is null (C7)
6. x = -1, y = 0 (C1, C5) and Square object is null (C7)
7. x = 0, y = 0 (C2, C5) and Square object is not null (C8 or C9)
8. x = width - 1, y = 0 (C2, C5) and Square object is not null (C8 or C9)
9. x = width, y = 0 (C3, C5) and Square object is null (C7)
10. x = width + 1, y = 0 (C3, C5) and Square object is null (C7)
11. x = -1, y = height - 1 (C1, C5) and Square object is null (C7)
12. x = 0, y = height - 1 (C2, C5) and Square object is not null (C8 or C9)
13. x = width - 1, y = height - 1 (C2, C5) and Square object is not null (C8 or C9)
14. x = width, y = height - 1 (C3, C5) and Square object is null (C7)
15. x = width + 1, y = height - 1 (C3, C5) and Square object is null (C7)
16. x = -1, y = height (C1, C6) and Square object is null (C7)
17. x = 0, y = height (C2, C6) and Square object is null (C7)
18. x = width - 1, y = height (C2, C6) and Square object is null (C7)
19. x = width, y = height (C3, C6) and Square object is null (C7)
20. x = width + 1, y = height (C3, C6) and Square object is null (C7)
21. x = -1, y = height + 1 (C1, C6) and Square object is null (C7)
22. x = 0, y = height + 1 (C2, C6) and Square object is null (C7)
23. x = width - 1, y = height + 1 (C2, C6) and Square object is null (C7)
24. x = width, y = height + 1 (C3, C6) and Square object is null (C7)
25. x = width + 1, y = height + 1 (C3, C6) and Square object is null (C7)

## 1.2 createBoard in the nl.tudelft.jpacman.board.BoardFactory class.

### Function

```java
public Board createBoard(Square[][] grid)

Creates a new board from a grid of cells and connects it.

Parameters:
grid - The square grid of cells, in which grid[x][y] corresponds to the square at position x,y.

Returns:
A new board, wrapping a grid of connected cells.
```

### Category-Partition
We can partition the input space into these categories:
- For grid:
    - C1: grid is null
    - C2: grid is not null and grid is empty
    - C3: grid is not null and grid has at least one null element
    - C4: grid is not null and grid has no null element
- For output:
    - C5: Board is null
    - C6: Board is not null and its height and width are not equal to the height and width of the grid
    - C7: Board is not null and its height and width are equal to the height and width of the grid

### Boundary Value Analysis
We can use the following values to test the function:
- For grid:
    - grid is null (C1)
    - grid.length = 0 (C2)
    - grid.length = 1 and grid[0][0] = null (C3)
    - grid.length = 1 and grid[0][0] != null (C4)
- For output:
    - board = null (C5)
    - board != null and board.height != grid.length or board.width != grid[0].length (C6)
    - board != null and board.height = grid.length and board.width = grid[0].length (C7)

### Test Cases
We can use the following test cases to test the function:
Given that grid.length = 10 and grid[0].length = 10:

1. grid = null (C1) and board is null (C5)
2. grid.length = 0 (C2) and board is null (C5)
3. grid.length = 1 and grid[0][0] = null (C3) and board is null (C5)
4. grid.length = 1 and grid[0][0] != null (C4) and board is not null and its height and width are not equal to the height and width of the grid (C6)
5. grid.length = 1 and grid[0][0] != null (C4) and board is not null and its height and width are equal to the height and width of the grid (C7)

## 1.3 *createLevel()* in the nl.tudelft.jpacman.level.LevelFactory class

### Function
```java
public Level createLevel(Board board, List<Ghost> ghosts, List<Square> startPositions)

Creates a new level from the provided data.

Parameters:
board - The board with all ghosts and pellets occupying their squares.
ghosts - A list of all ghosts on the board.
startPositions - A list of squares from which players may start the game.

Returns:
A new level for the board.
```

### Category-Partition
We can partition the input space into these categories:
- For board:
    - C1: board is null
    - C2: board is not null and board does not contain any pellets or ghosts
    - C3: board is not null and board contains at least one pellet or ghost
- For ghosts:
    - C4: ghosts is null
    - C5: ghosts is not null and ghosts is empty
    - C6: ghosts is not null and ghosts contains at least one ghost
- For startPositions:
    - C7: startPositions is null
    - C8: startPositions is not null and startPositions is empty
    - C9: startPositions is not null and startPositions contains at least one square
- For output:
    - C10: Level is null
    - C11: Level is not null and does not have a board
    - C12: Level is not null and has a board

### Boundary Value Analysis
We can use the following values to test the function:
- For board:
    - board = null (C1)
    - board != null and board.getGhostCount() = 0 and board.getPelletCount() = 0 (C2)
    - board != null and board.getGhostCount() > 0 or board.getPelletCount() > 0 (C3)
- For ghosts:
    - ghosts = null (C4)
    - ghosts != null and ghosts.size() = 0 (C5)
    - ghosts != null and ghosts.size() > 0 (C6)
- For startPositions:
    - startPositions = null (C7)
    - startPositions != null and startPositions.size() = 0 (C8)
    - startPositions != null and startPositions.size() > 0 (C9)
- For output:
    - level = null (C10)
    - level != null and level.getBoard() = null (C11)
    - level != null and level.getBoard() != null (C12)

### Test Cases
We can use the following test cases to test the function:

1. board = null (C1) and Level is null (C10)
2. board != null and board.getGhostCount() = 0 and board.getPelletCount() = 0 (C2) and Level is null (C10)
3. board != null and board.getGhostCount() > 0 or board.getPelletCount() > 0 (C3) and Level is not null and does not have a board (C11)

There are 3 * 3 * 3 = 27 test cases in total, considering all combinations of the categories.

## 1.4 *makeGhostSquare()* in the nl.tudelft.jpacman.level.MapParser class

### Function
```java
protected Square makeGhostSquare(List<Ghost> ghosts, Ghost ghost)

creates a Square with the specified ghost on it and appends the placed ghost into the ghost list.

Parameters:
ghosts - all the ghosts in the level so far, the new ghost will be appended
ghost - the newly created ghost to be placed

Returns:
a square with the ghost on it.
```

### Category-Partition
We can partition the input space into these categories:
- For ghosts:
    - C1: ghosts is null
    - C2: ghosts is not null and ghosts is empty
    - C3: ghosts is not null and ghosts contains at least one ghost
- For ghost:
    - C4: ghost is null
    - C5: ghost is not null and ghost.getSquare() is null
    - C6: ghost is not null and ghost.getSquare() is not null
- For output:
    - C7: Square is null
    - C8: Square is not null and square.getOccupants() is empty
    - C9: Square is not null and square.getOccupants() contains at least one ghost

### Boundary Value Analysis
We can use the following values to test the function:

1. ghosts = null (C1) and ghost = null (C4) and Square = null (C7)
2. ghosts != null and ghosts.size() = 0 (C2) and ghost = null (C4) and Square = null (C7)
3. ghosts != null and ghosts.size() > 0 (C3) and ghost = null (C4) and Square = null (C7)
4. ghosts = null (C1) and ghost != null and ghost.getSquare() = null (C5) and Square = null (C7)
5. ghosts != null and ghosts.size() = 0 (C2) and ghost != null and ghost.getSquare() = null (C5) and Square = null (C7)
6. ghosts != null and ghosts.size() > 0 (C3) and ghost != null and ghost.getSquare() = null (C5) and Square = null (C7)
7. ghosts = null (C1) and ghost != null and ghost.getSquare() != null (C6) and Square = null (C7)

## 1.5 *draw()* in the nl.tudelft.jpacman.sprite.ImageSprite class

### Function
```java
public void draw(Graphics graphics, int x, int y, int width, int height)

Draws the sprite on the provided graphics context.

Specified by:
draw in interface nl.tudelft.jpacman.sprite.Sprite

Parameters:
graphics - The graphics context to draw.
x - The destination x coordinate to start drawing.
y - The destination y coordinate to start drawing.
width - The width of the destination draw area.
height - The height of the destination draw area.
```

### Category-Partition
- For graphics:
    - C1: graphics is null
    - C2: graphics is not null
- For x:
    - C3: x < 0
    - C4: x >= 0 and x < width
    - C5: x >= width
- For y:
    - C6: y < 0
    - C7: y >= 0 and y < height
    - C8: y >= height
- For width:
    - C9: width < 0
    - C10: width >= 0
- For height:
    - C11: height < 0
    - C12: height >= 0

### Boundary Value Analysis
We can use the following values to test the function:
- For graphics:
    - graphics = null (C1)
    - graphics != null (C2)
- For x:
    - x = -1 (C3)
    - x = 0 and x < width (C4)
    - x = width (C5)
    - x = width + 1 (C5)
- For y:
    - y = -1 (C6)
    - y = 0 and y < height (C7)
    - y = height (C8)
    - y = height + 1 (C8)
- For width:
    - width = -1 (C9)
    - width = 0 (C10)
    - width = 1 (C10)
- For height:
    - height = -1 (C11)
    - height = 0 (C12)
    - height = 1 (C12)

### Test Cases
We can use the following test cases to test the function:

1. graphics = null (C1) and x = -1 (C3) and y = -1 (C6) and width = -1 (C9) and height = -1 (C11)
2. graphics != null (C2) and x = -1 (C3) and y = -1 (C6) and width = -1 (C9) and height = -1 (C11)
3. graphics != null (C2) and x = 0 (C4) and y = -1 (C6) and width = -1 (C9) and height = -1 (C11)
4. graphics != null (C2) and x = width (C5) and y = -1 (C6) and width = -1 (C9) and height = -1 (C11)
5. graphics != null (C2) and x = width + 1 (C5) and y = -1 (C6) and width = -1 (C9) and height = -1 (C11)

There are 2*5*5*3*3 = 450 test cases.
