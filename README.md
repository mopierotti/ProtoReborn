This is a quick example of how to structure a render-time independent main game loop as inspired by Glenn Fiedler (http://gafferongames.com/game-physics/fix-your-timestep/).  Essentially, this allows your engine to trigger variable amounts of game engine ticks relative to the speed that frames happen to be rendering at at any given moment in time.

There's also some quick and dirty code to render a tiled gutter pattern on the edges of the game window to maintain a constant aspect ratio when users resize the game window.
