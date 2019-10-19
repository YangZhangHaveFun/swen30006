package strategy;

import com.badlogic.gdx.graphics.g3d.particles.ParticleSorter;
import tiles.MapTile;
import utilities.Coordinate;
import world.Car;
import world.WorldSpatial;

import java.util.*;

public class aStarSearchStrategy {
    private int wallSensitivity;
    private Car car;

    public aStarSearchStrategy(int wallSensitivity, Car car) {
        this.wallSensitivity = wallSensitivity;
        this.car = car;
    }

    public static enum MyDirection{HEAD, LEFT, RIGHT};

    public LinkedList<MyDirection> aStarSearch(Coordinate currentPosition, Coordinate goalPosition, HashMap<Coordinate, MapTile> currentView){
        long startTime = System.nanoTime();
        int cost = 0;
        int priority = currentPosition.getManhatanDistance(goalPosition);
        PriorityQueue<SearchNode> frontier = new PriorityQueue<>();
        LinkedList<MyDirection> action = new LinkedList<>();
        SearchNode initialNode = new SearchNode(currentPosition, goalPosition, action, cost, priority, currentView);
        frontier.add(initialNode);
        LinkedList<Coordinate> visited = new LinkedList<>();
        //SearchNode last_log = initialNode;

        while (!frontier.isEmpty() || System.nanoTime()-startTime/100000000 >= 2){
            SearchNode currentNode = frontier.poll();
            Coordinate currentPos = currentNode.initialPosition;
            //last_log = currentNode;
            if (currentPos.equals(goalPosition)){
                if (currentNode.getActions().size() > 0)
                    return currentNode.getActions();
                else
                    return getRandomElement(getLegalActions(getOrientation(),currentView, currentPos));
            }

            if (!visited.contains(currentPos)){
                LinkedList<MyDirection> actions = getLegalActions(getOrientation(),currentView, currentPos);
                for (MyDirection direction: actions){
                    Coordinate nextPosition = getNextPosition(currentPos,getOrientation(), direction, car.getVelocity());
                    if (!visited.contains(nextPosition)){
                        int newPriority = nextPosition.getManhatanDistance(goalPosition);
                        LinkedList<MyDirection> nextAction = new LinkedList<>(currentNode.getActions());
                        nextAction.addLast(direction);
                        frontier.add(new SearchNode(nextPosition,goalPosition, nextAction, cost+car.getVelocity(), newPriority, currentView));
                    }
                }
            }
            visited.addLast(currentPos);
        }
        return null;
    }


    private LinkedList<MyDirection> getLegalActions(WorldSpatial.Direction direction, HashMap<Coordinate, MapTile> currentView, Coordinate currentPosition){
        LinkedList<MyDirection> legalActions = new LinkedList<>();
        for (MyDirection orientation: MyDirection.values()){
            switch(orientation){
                case HEAD:
                    if (!checkWallAhead(direction, currentView, currentPosition))
                        legalActions.add(orientation);
                    break;
                case LEFT:
                    if (!checkLeftWall(direction, currentView, currentPosition))
                        legalActions.add(orientation);
                    break;
                case RIGHT:
                    if (!checkRightWall(direction, currentView, currentPosition))
                        legalActions.add(orientation);
                    break;
            }
        }
        return legalActions;
    }

    /**
     * Check if you have a wall in front of you!
     * @param orientation the orientation we are in based on WorldSpatial
     * @param currentView what the car can currently see
     * @return
     */
    private boolean checkWallAhead(WorldSpatial.Direction orientation, HashMap<Coordinate, MapTile> currentView, Coordinate currentPosition){
        switch(orientation){
            case EAST:
                return checkEast(currentView, currentPosition);
            case NORTH:
                return checkNorth(currentView, currentPosition);
            case SOUTH:
                return checkSouth(currentView, currentPosition);
            case WEST:
                return checkWest(currentView, currentPosition);
            default:
                return false;
        }
    }

    /**
     * Check if the wall is on your left hand side given your orientation
     * @param orientation
     * @param currentView
     * @return
     */
    private boolean checkLeftWall(WorldSpatial.Direction orientation, HashMap<Coordinate, MapTile> currentView, Coordinate currentPosition) {

        switch(orientation){
            case EAST:
                return checkNorth(currentView, currentPosition);
            case NORTH:
                return checkWest(currentView, currentPosition);
            case SOUTH:
                return checkEast(currentView, currentPosition);
            case WEST:
                return checkSouth(currentView, currentPosition);
            default:
                return false;
        }
    }

    /**
     * Check if the wall is on your left hand side given your orientation
     * @param orientation
     * @param currentView
     * @return
     */
    private boolean checkRightWall(WorldSpatial.Direction orientation, HashMap<Coordinate, MapTile> currentView, Coordinate currentPosition) {

        switch(orientation){
            case EAST:
                return checkSouth(currentView, currentPosition);
            case NORTH:
                return checkEast(currentView, currentPosition);
            case SOUTH:
                return checkWest(currentView, currentPosition);
            case WEST:
                return checkNorth(currentView, currentPosition);
            default:
                return false;
        }
    }

    /**
     * Method below just iterates through the list and check in the correct coordinates.
     * i.e. Given your current position is 10,10
     * checkEast will check up to wallSensitivity amount of tiles to the right.
     * checkWest will check up to wallSensitivity amount of tiles to the left.
     * checkNorth will check up to wallSensitivity amount of tiles to the top.
     * checkSouth will check up to wallSensitivity amount of tiles below.
     */
    public boolean checkEast(HashMap<Coordinate, MapTile> currentView, Coordinate currentPosition){
        // Check tiles to my right
        //Coordinate currentPosition = new Coordinate(getPosition());
        for(int i = 0; i <= wallSensitivity; i++){
            MapTile tile = currentView.get(new Coordinate(currentPosition.x+i, currentPosition.y));
            if(tile.isType(MapTile.Type.WALL)){
                return true;
            }
        }
        return false;
    }

    public boolean checkWest(HashMap<Coordinate,MapTile> currentView, Coordinate currentPosition){
        // Check tiles to my left
        // Coordinate currentPosition = new Coordinate(getPosition());
        for(int i = 0; i <= wallSensitivity; i++){
            MapTile tile = currentView.get(new Coordinate(currentPosition.x-i, currentPosition.y));
            if(tile.isType(MapTile.Type.WALL)){
                return true;
            }
        }
        return false;
    }

    public boolean checkNorth(HashMap<Coordinate,MapTile> currentView, Coordinate currentPosition){
        // Check tiles to towards the top
        // Coordinate currentPosition = new Coordinate(getPosition());
        for(int i = 0; i <= wallSensitivity; i++){
            MapTile tile = currentView.get(new Coordinate(currentPosition.x, currentPosition.y+i));
            if(tile.isType(MapTile.Type.WALL)){
                return true;
            }
        }
        return false;
    }

    public boolean checkSouth(HashMap<Coordinate,MapTile> currentView, Coordinate currentPosition){
        // Check tiles towards the bottom
        // Coordinate currentPosition = new Coordinate(getPosition());
        for(int i = 0; i <= wallSensitivity; i++){
            MapTile tile = currentView.get(new Coordinate(currentPosition.x, currentPosition.y-i));
            if(tile.isType(MapTile.Type.WALL)){
                return true;
            }
        }
        return false;
    }

    public WorldSpatial.Direction getOrientation(){
        return car.getOrientation();
    }

    public Coordinate getNextPosition(Coordinate currentPosition, WorldSpatial.Direction orientation, MyDirection direction, int speed){
        switch (orientation){
            case EAST:
                switch (direction){
                    case HEAD:
                        return new Coordinate(currentPosition.x+speed, currentPosition.y);
                    case LEFT:
                        return new Coordinate(currentPosition.x, currentPosition.y+speed);
                    case RIGHT:
                        return new Coordinate(currentPosition.x, currentPosition.y-speed);
                }
            case NORTH:
                switch (direction){
                    case HEAD:
                        return new Coordinate(currentPosition.x, currentPosition.y+speed);
                    case LEFT:
                        return new Coordinate(currentPosition.x-speed, currentPosition.y);
                    case RIGHT:
                        return new Coordinate(currentPosition.x+speed, currentPosition.y);
                }
            case SOUTH:
                switch (direction){
                    case HEAD:
                        return new Coordinate(currentPosition.x, currentPosition.y-speed);
                    case LEFT:
                        return new Coordinate(currentPosition.x+speed, currentPosition.y);
                    case RIGHT:
                        return new Coordinate(currentPosition.x-speed, currentPosition.y);
                }
            case WEST:
                switch (direction){
                    case HEAD:
                        return new Coordinate(currentPosition.x-speed, currentPosition.y);
                    case LEFT:
                        return new Coordinate(currentPosition.x, currentPosition.y-speed);
                    case RIGHT:
                        return new Coordinate(currentPosition.x, currentPosition.y+speed);
                }
            default:
                return currentPosition;
        }
    }

    public LinkedList<MyDirection> getRandomElement(LinkedList<MyDirection> list)
    {
        Random rand = new Random();
        MyDirection direction = list.get(rand.nextInt(list.size()));
        LinkedList<MyDirection> singleElementList = new LinkedList<>();
        singleElementList.addLast(direction);
        return singleElementList;
    }

    class SearchNode implements Comparable<SearchNode>{
        private Coordinate initialPosition;
        private Coordinate destPosition;
        private LinkedList<MyDirection> actions;
        private int cost;
        private int priority;
        private HashMap<Coordinate, MapTile> currentView;

        public SearchNode(Coordinate initialPosition, Coordinate destPosition,
                          LinkedList<MyDirection> actions, int cost, int priority,
                          HashMap<Coordinate, MapTile> currentView) {
            this.initialPosition = initialPosition;
            this.destPosition = destPosition;
            this.actions = actions;
            this.cost = cost;
            this.priority = priority;
            this.currentView = currentView;
            this.priority = initialPosition.getManhatanDistance(destPosition);
        }

        @Override
        public int compareTo(SearchNode node) {
            return this.priority - node.getPriority();
        }

        public Coordinate getInitialPosition() {
            return initialPosition;
        }

        public Coordinate getDestPosition() {
            return destPosition;
        }

        public LinkedList<MyDirection> getActions() {
            return actions;
        }

        public HashMap<Coordinate, MapTile> getCurrentView() {
            return currentView;
        }

        public int getCost() {
            return cost;
        }

        public int getPriority() {
            return priority;
        }
    }
}

