import java.util.ArrayList;

class Snake
{
    int length;
    boolean placed = false;
    private int startX, startY;

    Solver solver;

    int currentScore;

    String output = "";

    // head is at index 0
    ArrayList<Coord> coords;

    public Snake(int _length, Solver _solver)
    {
        this.solver = _solver;
        this.length = _length;
    }

    public void place(Coord coord)
    {
        if (placed)
        {
            System.out.println("Tried placing already placed snake at " + coord.x + ", " + coord.y);
            return;
        }
        coords = new ArrayList<>();
        placed = true;
        startX = coord.x;
        startY = coord.y;
        output = startX + " " + startY + " ";
        addNodeAtHead(new Coord(coord.y, coord.x));
    }

    enum Direction
    {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    // returns opposite of is node occupied
    public boolean checkCanMove(Coord coord)
    {
        return !solver.map[coord.y][coord.x].isOccupied;
    }

    private void addNodeAtHead(Coord coord)
    {
        coords.add(0, coord.clone());
        solver.map[coord.y][coord.x].isOccupied = true;
    }

    private void removeTail()
    {
        Coord coord = coords.remove(coords.size() - 1);
        solver.map[coord.y][coord.x].isOccupied = false;
    }

    public Coord getHead()
    {
        return coords.get(0);
    }

    public void move(Direction d)
    {
        int newX, newY;
        String ogOut = output;
        switch (d)
        {
            case UP:
                newX = getHead().x;
                newY = getHead().y - 1;

                output += "U ";
                break;
            case DOWN:
                newX = getHead().x;
                newY = getHead().y + 1;
                output += "D ";
                break;
            case LEFT:
                newX = getHead().x - 1;
                newY = getHead().y;
                output += "L ";
                break;
            case RIGHT:
                newX = getHead().x + 1;
                newY = getHead().y;
                output += "R ";
                break;
            default: // if we get a weird error
                System.out.println("Invalid direction given");
                return;
        }

        if (newX >= solver.map[0].length)
        {
            newX = 0;
        }
        if (newY >= solver.map.length)
        {
            newY = 0;
        }
        if (newX < 0)
        {
            newX = solver.map[0].length - 1;
        }
        if (newY < 0)
        {
            newY = solver.map.length - 1;
        }


        if (checkCanMove(new Coord(newY, newX)))
        {
            addNodeAtHead(new Coord(newY, newX));
            if (coords.size() > length)
            {
                removeTail();
            }
            if (solver.map[newY][newX].isWormhole)
            {
                output += newX + " " + newY + " ";
            }
        } else
        {
            // remove earlier output
            output = ogOut;
        }
        recalculateScore();
    }

    public void recalculateScore()
    {
        // do things
        currentScore = 0;
        for (int i = 0; i < coords.size(); i++)
        {
            Coord c = coords.get(i);
            Node n = solver.map[c.y][c.x];
            if (n.isWormhole)
            {
                continue;
            }
            currentScore += n.value;
        }
        currentScore = 0;
    }


}
