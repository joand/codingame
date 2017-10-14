var inputs = readline().split(' ');
var L = parseInt(inputs[0]);
var C = parseInt(inputs[1]);
const grid = new Array(L);
for (var i = 0; i < L; i++) {
    grid[i] = readline().split('');
    // console.log(grid[i].join(''));
}

function findStart() {
    for(let r = 0; r < L; r++) {
        for(let c = 0; c < C; c++) {
            if(grid[r][c] === '@') {
                return { r, c };
            }
        }
    }
}

function findOtherTeleporter(state) {
    for(let r = 0; r < L; r++) {
        for(let c = 0; c < C; c++) {
            if(grid[r][c] === 'T' && !(r === state.r && c === state.c)) {
                return { r, c };
            }
        }
    }
}

function tryMove(state) {
    const { direction } = state;
    let rOffset, cOffset;
    switch(direction) {
        case 'SOUTH': {
            rOffset = 1;
            cOffset = 0;
            break;
        }
        case 'EAST': {
            rOffset = 0;
            cOffset = 1;
            break;
        }
        case 'NORTH': {
            rOffset = -1;
            cOffset = 0;
            break;
        }
        case 'WEST': {
            rOffset = 0;
            cOffset = -1;
            break;
        }
    }
    const char = grid[state.r + rOffset][state.c + cOffset];
    if(char === '#' || (char === 'X' && !state.breaker)) return false;
    state.r = state.r + rOffset;
    state.c = state.c + cOffset;
    return true;
}

function setNextDirection(state, currentAttempt) {
    let priorities = ['SOUTH', 'EAST', 'NORTH', 'WEST'];
    if(state.inverted) priorities = priorities.reverse();
    state.direction = priorities[currentAttempt];
    return true;
}

function stepOnField(state) {
    switch(grid[state.r][state.c]) {
        case 'S': {
            state.direction = 'SOUTH';
            break;
        }
        case 'E': {
            state.direction = 'EAST';
            break;
        }
        case 'N': {
            state.direction = 'NORTH';
            break;
        }
        case 'W': {
            state.direction = 'WEST';
            break;
        }
        case 'I': {
            state.inverted = !state.inverted;
            break
        }
        case 'B': {
            state.breaker = !state.breaker;
            break;
        }
        case 'X': {
            // we are in breaker mode and kill X
            grid[state.r][state.c] = ' ';
            break;
        }
        case 'T': {
            const { r, c } = findOtherTeleporter(state)
            state.r = r;
            state.c = c;
            break;
        }
    }
}

const { r, c } = findStart()
const state = {
    direction: 'SOUTH',
    breaker: false,
    inverted: false,
    r,
    c,
    // must be part of state, because we can break obstacles
    // in breaker mode
    grid
}

const existingStates = new Map();
let answers = []
let run = true
while(run) {
    existingStates.set(JSON.stringify(state), true);
    let moved = false
    let attempt = -1;
    do {
        moved = tryMove(state);
        attempt += 1;
    } while(!moved && setNextDirection(state, attempt));
    answers.push(state.direction);
    stepOnField(state);

    if(existingStates.get(JSON.stringify(state))) {
        run = false;
        answers = ['LOOP'];
    }
    else run = grid[state.r][state.c] !== '$';
}

print(answers.join('\n'));