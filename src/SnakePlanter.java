import java.util.ArrayList;
import java.util.List;

public class SnakePlanter
{

    static int numOfSnakes;

    public static void plantSnakes(Node[][] map, Snake[] snakes)
    {
        numOfSnakes = snakes.length;
        List<Coord> snakePlantCoords = findSnakePlants(map, snakes);

        for (int i = 0; i < snakes.length; i++)
        {
            Snake snake = snakes[i];
            Coord c = snakePlantCoords.get(i);
            Node snakePlant = map[c.y][c.x];
            Coord coord = getSnakePlantCoord(map, snakePlant);
//            System.out.println("x: " + coord.x + " y: " + coord.y + " = " + map[coord.y][coord.x].value);
            snake.place(coord);
        }
    }

    private static Coord getSnakePlantCoord(Node[][] map, Node node)
    {
        for (int y = 0; y < map.length; y++)
        {
            for (int x = 0; x < map[y].length; x++)
            {
                if (map[y][x] == node)
                {
                    return new Coord(y, x);
                }
            }

        }
        return null;
    }

    private static List<Coord> findSnakePlants(Node[][] map, Snake[] snakes)
    {
//        List<Node> snakePlants = new ArrayList<>();
        List<Coord> snakePlantCoords = new ArrayList<>();

        for (int y = 0; y < map.length; y++)
        {
            for (int x = 0; x < map[y].length; x++)
            {
                Node node = map[y][x];
                if (node.isWormhole)
                {
                    continue;
                }
                for (int j = 0; j < numOfSnakes; j++)
                {
                    if (snakePlantCoords.size() == 0 || snakePlantCoords.size() <= j || snakePlantCoords.get(j) == null)
                    {
                        snakePlantCoords.add(j, new Coord(y, x));
                        break;
                    }
                    Coord c = snakePlantCoords.get(j);
                    if (node.value > map[c.y][c.x].value)
                    {
                        snakePlantCoords.add(j, new Coord(y, x));
                        break;
                    }
                }
                if (snakePlantCoords.size() > numOfSnakes)
                {
                    snakePlantCoords = snakePlantCoords.subList(0, numOfSnakes);
                }

                    /*Node lowestScoreNode = getLowestSnakePlantScore(snakePlants);
                    if (lowestScoreNode != null && node.value > lowestScoreNode.value)
                    {//this means node is worth enough to be added
                        if (snakePlants.size() == snakes.length)
                        {
                            //replace the lowest value for this
                            int lowestScoreIndex = snakePlants.indexOf(lowestScoreNode);
                            snakePlants.set(lowestScoreIndex, node);
                        } else
                        {
                            //append node onto list because there is more room
                            snakePlants.add(node);
                        }
                    } else
                    {
                        snakePlants.add(node);
                    }*/
            }
        }

        return snakePlantCoords;
    }

    private static Node getLowestSnakePlantScore(List<Node> snakePlants)
    {
        if (snakePlants.size() <= 0)
        {
            return null;
        }
        Node minNode = snakePlants.get(0); // set the current minimum to the first element
        for (int i = 1; i < snakePlants.size(); i++)
        {
            if (snakePlants.get(i).value < minNode.value)
            {
                minNode = snakePlants.get(i); // update the minimum if a smaller element is found
            }
        }
        return minNode;
    }
}
