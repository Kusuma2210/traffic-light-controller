model/ LightState -RED->GREEN->YELLOW
Direction - NORTH,SOUTH,EAST,WEST
TrafficLight - Direction,LightState
SignalHistory- Event,TimeStamp

dto/ ChangeSignalRequest,CurrentStateReponse

service/ ThreadSafe via ReentrantLock + CopyOnWriteArrayList

controller/ TrafficLightController- REST API
