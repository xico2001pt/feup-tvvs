# Modified Condition/Decision Coverage (MC/DC) - Week 5

## 1. Perform ‘Modified Condition/Decision Coverage (MC/DC)’

### 1.1 withinBorders function in the nl.tudelft.jpacman.board.Board class

#### Source

```java
/**
 * Determines whether the given <code>x,y</code> position is on this board.
 *
 * @param x
 *            The <code>x</code> position (row) to test.
 * @param y
 *            The <code>y</code> position (column) to test.
 * @return <code>true</code> iff the position is on this board.
 */
public boolean withinBorders(int x, int y) {
    return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
}
```

#### Decision Table

If there was no lazy evaluation, there would be 2⁴ = 16 tests.
Assuming lazy evaluation, the decision table is as follows:

| Test | x>=0 | x<getWidth() | y>=0 | y<getHeight() | Decision |
| ---- | ---- | ------------ | ---- | ------------- | -------- |
| 1    | T    | T            | T    | T             | T        |
| 2    | T    | T            | T    | F             | F        |
| 3    | T    | T            | F    | -             | F        |
| 4    | T    | F            | -    | -             | F        |
| 5    | F    | -            | -    | -             | F        |

The independent pais for each condition are: 
- x>= 0 -> {t1, t5}
- x<getWidth() -> {t1, t4}
- y>=0 -> {t1, t3}
- y<getHeight() -> {t1, t2}

In this case, all tests are necessary to cover all conditions.

### 1.2 start function in the nl.tudelft.jpacman.game.Game class

#### Source

```java
/**
 * Starts or resumes the game.
 */
public void start() {
    synchronized (progressLock) {
        if (isInProgress()) {
            return;
        }
        if (getLevel().isAnyPlayerAlive() && getLevel().remainingPellets() > 0) {
            inProgress = true;
            getLevel().addObserver(this);
            getLevel().start();
        }
    }
}
```

#### Decision Tables

If there was no lazy evaluation, there would be 2+2²=6 tests.
Assuming lazy evaluation, the decision table for the first condition is as follows:

| Test | isInProgress() | Decision |
| ---- | -------------- | -------- |
| 1    | T              | T        |
| 2    | F              | F        |

Where all tests are obviously necessary.

And for the second condition:

| Test | isAnyPlayerAlive() | remainingPellets() > 0 | Decision |
| ---- | ------------------ | ---------------------- | -------- |
| 1    | T                  | T                      | T        |
| 2    | T                  | F                      | F        |
| 3    | F                  | -                      | F        |

The independent pairs for each condition are:
- isAnyPlayerAlive() -> {t1, t3}
- remainingPellets() > 0 -> {t1, t2}

And also all the tests are necessary to cover all conditions.

## 2. Exercise: write unit tests

The tests for 1.1 are implemented in `BoardTest.java` named `testBoarders`.

The tests for 1.2 are implemented in `GameTest.java` named `testGameStart`.