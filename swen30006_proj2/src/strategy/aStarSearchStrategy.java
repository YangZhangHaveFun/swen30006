package strategy;

import com.badlogic.gdx.graphics.g3d.particles.ParticleSorter;
import tiles.MapTile;
import utilities.Coordinate;
import world.Car;
import world.WorldSpatial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class aStarSearchStrategy {
    private int wallSensitivity;

    public aStarSearchStrategy(int wallSensitivity) {
        this.wallSensitivity = wallSensitivity;
    }

    public LinkedList<WorldSpatial.RelativeDirection> aStarSearch(Coordinate currentPosition, Coordinate goalPosition, HashMap<Coordinate, MapTile> currentView){
        start = time.time()
        int cost = 0;
        int priority = 0;
        PriorityQueue<SearchNode> frontier = new PriorityQueue<>();
        LinkedList<WorldSpatial.RelativeDirection> action = new LinkedList<>();
        SearchNode initialNode = new SearchNode(currentPosition, goalPosition, action, cost, priority, currentView);
        frontier.add(initialNode);
        LinkedList<Coordinate> visited = new LinkedList<>();
        SearchNode last_log = initialNode;

        while (!frontier.isEmpty()){
            SearchNode currentNode = frontier.poll();
            Coordinate currentPos = currentNode.initialPosition;
            last_log = currentNode;
            if (currentPos.equals(goalPosition)){
                if (currentNode.getActions().size() > 0)
                    return currentNode.getActions();
                else
                    return null;//TODO： find legal actions
            }

            if (visited.contains(currentPos)){
                //TODO: find legal actions

            }


        }
        currentState, currentPosition, currentAction, currentCost = frontier.pop()
        last_log = (currentState, currentPosition, currentAction, currentCost)

        if currentPosition == goal :
        if len(currentAction) > 0:
        self.actionList = currentAction
        return self.actionList
            else:
        self.actionList = currentState.getLegalActions(self.index)
        return self.actionList
            # break

        # if (currentPosition not in visited) and (currentPosition in legalPositions):
        if (currentPosition not in visited):
        actions = currentState.getLegalActions(self.index)
        for action in actions:
        nextState = self.getSuccessor(currentState, action)
        nextPosition = nextState.getAgentState(self.index).getPosition()
        x, y = nextPosition
        if (self.red and x >= gameState.data.layout.width / 2) or (not self.red and x <
                gameState.data.layout.width / 2):
        continue
                cost = 1
        if nextPosition not in visited:
        priority = self.getMazeDistance(nextPosition, goal)
        frontier.push((nextState, nextPosition, currentAction + [action], currentCost + cost), priority)

        visited.append(currentPosition)

        print('the open list is empty yet not at the goal state')
        _, _, best_actions, _ = last_log
        if 'Stop' in best_actions:
        best_actions.remove('Stop')
        return best_actions
    }


    private ArrayList<WorldSpatial.Direction> getLegalActions(HashMap<Coordinate, MapTile> currentView, Coordinate currentPosition){
        ArrayList<WorldSpatial.Direction> legalActions = new ArrayList<>();
        for (WorldSpatial.Direction orientation: WorldSpatial.Direction.values()){
            switch(orientation){
                case EAST:
                    if (!checkEast(currentView, currentPosition))
                        legalActions.add(orientation);
                    break;
                case NORTH:
                    if (!checkNorth(currentView, currentPosition))
                        legalActions.add(orientation);
                    break;
                case SOUTH:
                    if (!checkSouth(currentView, currentPosition))
                        legalActions.add(orientation);
                    break;
                case WEST:
                    if (!checkWest(currentView, currentPosition))
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

    class SearchNode implements Comparable<SearchNode>{
        private Coordinate initialPosition;
        private Coordinate destPosition;
        private LinkedList<WorldSpatial.RelativeDirection> actions;
        private int cost;
        private int priority;
        private HashMap<Coordinate, MapTile> currentView;

        public SearchNode(Coordinate initialPosition, Coordinate destPosition,
                          LinkedList<WorldSpatial.RelativeDirection> actions, int cost, int priority,
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

        public LinkedList<WorldSpatial.RelativeDirection> getActions() {
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

