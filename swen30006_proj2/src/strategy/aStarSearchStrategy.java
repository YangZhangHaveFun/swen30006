package strategy;

import tiles.MapTile;
import utilities.Coordinate;
import world.Car;
import world.WorldSpatial;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class aStarSearchStrategy {
    private int wallSensitivity;

    /*
    def waStar(self, gameState, goal, initialPosition):
    '''
    This method is to let agent directly head towards the border when it in its own terrain,
    and the heuristic used in this method is maze distance
    :param gameState:
    :param goal: one point on the border
    :param initialPosition: current position of the agent
    :return: path
    '''
    # print('method waStar get called')
    # self.actionList = gameState.getLegalActions(self.index)
    start = time.time()
    cost = 0
    priority = 0
    frontier = util.PriorityQueue()
    action = []

    frontier.push((gameState, initialPosition, action, cost), priority)
    visited = []
    last_log = (gameState, initialPosition, action, cost)

    while not frontier.isEmpty():
        currentState, currentPosition, currentAction, currentCost = frontier.pop()
        last_log = (currentState, currentPosition, currentAction, currentCost)

        if currentPosition == goal or (time.time() - start > 0.8):
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
    # self.actionList = currentState.getLegalActions(self.index)
    # print('randomly choose an action in a star')
    # return self.actionList
 */
    public LinkedList<WorldSpatial.RelativeDirection> aStarSearch(Coordinate currentPosition, Coordinate goalPosition){
        start = time.time()
        int cost = 0;
        int priority = 0;
        PriorityQueue<SearchNode> frontier = new PriorityQueue();
        LinkedList<WorldSpatial.RelativeDirection> action = new LinkedList<>();

        frontier.push((gameState, initialPosition, action, cost), priority)
        LinkedList<Coordinate> visited = new LinkedList<>();
        SearchNode last_log = new SearchNode(); (gameState, initialPosition, action, cost)

        while (!frontier.isEmpty()){
            SearchNode currentNode = frontier.poll();
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
    private boolean checkFollowingWall(WorldSpatial.Direction orientation, HashMap<Coordinate, MapTile> currentView, Coordinate currentPosition) {

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

    class SearchNode implements Comparable{
        Coordinate initialPosition;
        Coordinate destPosition;
        WorldSpatial.RelativeDirection relativeDirection;
        int cost;
        int priority = initialPosition.getManhatanDistance(destPosition);


        @Override
        public int compareTo(Object o) {
            return (SearchNode o) ;
        }

        public Coordinate getInitialPosition() {
            return initialPosition;
        }

        public Coordinate getDestPosition() {
            return destPosition;
        }

        public WorldSpatial.RelativeDirection getRelativeDirection() {
            return relativeDirection;
        }

        public int getCost() {
            return cost;
        }

        public int getPriority() {
            return priority;
        }
    }
}

