import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Solver
{
    Node[][] map;
    String inputFile;
    private int cols, rows, numOfSnakes;

    Snake[] snakes;


    public Solver(String input)
    {
        System.out.println("solving: " + input);
        inputFile = input;
    }

    public void writeToFile()
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile + ".output.txt"));
            for (int i = 0; i < snakes.length; i++)
            {
                Snake s = snakes[i];
                writer.write(s.output.trim());
                if (i != snakes.length - 1)
                    writer.write("\n");
            }
            writer.close();
        } catch (Exception e)
        {
            System.out.println("couldnt write file");
        }
    }

    public void solve()
    {
        readData();
        SnakePlanter.plantSnakes(map, snakes);
        moveSnakes();
        writeToFile();
    }

    public void moveSnakes()
    {
        // brute force?
        for (int i = 0; i < snakes.length; i++)
        {
            Snake s = snakes[i];
            for (int j = 0; j < 10000; j++)
            {
                Random random = new Random();
                int d = random.nextInt(4);
                if (d == 0)
                {
                    s.move(Snake.Direction.UP);
                }
                if (d == 1)
                {
                    s.move(Snake.Direction.RIGHT);
                }
                if (d == 2)
                {
                    s.move(Snake.Direction.LEFT);
                }
                if (d == 3)
                {
                    s.move(Snake.Direction.DOWN);
                }
                if (s.coords.size() == s.length)
                {
                    break;
                }

            }
        }
    }

//    public void plantSnakes()
//    {
//        for (int i = 0; i < snakes.length; i++)
//        {
//            Snake snake = snakes[i];
//            Node snakePlant = snakePlants.get(i);
//            Coord coord = getSnakePlantCoord(snakePlant);
//            snake.place(coord);
//        }
//    }
//
//    private Coord getSnakePlantCoord(Node node){
//        for (int y = 0; y < map.length; y++)
//        {
//            for (int x = 0; x < map[y].length; x++)
//            {
//                if (map[y][x] == node){
//                    return new Coord(y, x);
//                }
//            }
//
//        }
//        return null;
//    }
//
//    private void findSnakePlants()
//    {
//        for (int i = 0; i < snakes.length; i++)
//        {
//            for (int y = 0; y < map.length; y++)
//            {
//                for (int x = 0; x < map[i].length; x++)
//                {
//                    Node node = map[y][x];
//                    Node lowestScoreNode = getLowestSnakePlantScore();
//                    if (node.value > lowestScoreNode.value)
//                    {//this means node is worth enough to be added
//                        if (snakePlants.size() == snakes.length)
//                        {
//                            //replace the lowest value for this
//                            int lowestScoreIndex = snakePlants.indexOf(lowestScoreNode);
//                            snakePlants.set(lowestScoreIndex, node);
//                        } else
//                        {
//                            //append node onto list because there is more room
//                            snakePlants.add(node);
//                        }
//                    }
//                }
//
//
//            }
//        }
//    }
//
//    private Node getLowestSnakePlantScore()
//    {
//        Node minNode = snakePlants.get(0); // set the current minimum to the first element
//        for (int i = 1; i < snakePlants.size(); i++)
//        {
//            if (snakePlants.get(i).value < minNode.value)
//            {
//                minNode = snakePlants.get(i); // update the minimum if a smaller element is found
//            }
//        }
//        return minNode;
//    }

    public void generateMap(BufferedReader reader)
    {
        try
        {
            String line;
            int currentRow = 0;
            map = new Node[rows][cols];
            while ((line = reader.readLine()) != null)
            {
                String[] vals = line.split(" ");
                for (int i = 0; i < vals.length; i++)
                {
                    Node node = new Node();
                    if (vals[i].equals("*"))
                    {
                        node.isWormhole = true;
                    } else
                    {
                        node.value = Integer.parseInt(vals[i]);
                    }
                    map[currentRow][i] = node;
                }
                currentRow++;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void readData()
    {
        try
        {
//            List<List<Integer>>
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            String firstLine = reader.readLine();


            // gets first line data and parses
            String[] strings = firstLine.split(" ");
            int[] values = new int[strings.length];
            for (int i = 0; i < strings.length; i++)
            {
                values[i] = Integer.parseInt(strings[i]);
            }
            cols = values[0];
            rows = values[1];
            numOfSnakes = values[2];

            snakes = new Snake[numOfSnakes];

            // parses second line of snake lengths
            String secondLine = reader.readLine();
            String[] snakeStrings = secondLine.split(" ");
            for (int i = 0; i < snakeStrings.length; i++)
            {
                snakes[i] = new Snake(Integer.parseInt(snakeStrings[i]), this);
            }

            generateMap(reader);


        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

}
