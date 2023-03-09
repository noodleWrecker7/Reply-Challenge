import java.util.Objects;

public class Coord
{

    int x, y;

    public Coord(int y, int x)
    {
        this.x = x;
        this.y = y;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        return x == coord.x && y == coord.y;
    }




    public Coord clone()
    {
        return new Coord(y, x);
    }


}
