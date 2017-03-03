import java.util.*;
import java.util.stream.Collectors;
import java.util.Objects;

/**
 * sources :
 * http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
 * https://mail-attachment.googleusercontent.com/attachment/u/0/?ui=2&ik=26879a90be&view=att&th=13a50271fc085591&attid=0.2&disp=inline&realattid=f_h85xz9341&safe=1&zw&saddbat=ANGjdJ80ygnCoNeDC9IW_BeMrbaHsPMOyCZVHVhJiRIb3Bx7zYzvotbkSu4Ccl7L0bQ1g5MU-3qVVpz8J1r1gDKNr8XmsVwUn2OFyRsKXsmPmN5ByoMPdpR6J6jnoAKCBl0NXk3N3z-z_JNVARkc-Hx5ES9qBL5FA-ZKaTmuzSeKpKxmNSdFFFnv2iKayFpJbUzhB5imQxy4e9jxrpdAxvt7ae91iAwECj5g2gu0ESl05x9Mrb2ydh-gwlMsZq8xyilMa4eAQnXVjdYsQSbQ3KhNgEozfBzXqxRo7TjHNK_ok2Ar0Qy9aPtoLFTdwh4T74kZ-yDJJiul1mPTobasPWv1oKBW76oaOybAqmnkbdbwkcgqMUgYs8r0iF3xsXfOavLfhJsnWi8ElxvrIMpgEsbMsyV9UXRGm3C-wG0QqNWfwVeLanr5ZcIIBlOs1LDY9xYQ-Vs0Xco1QfkCQydWI_vjGsbAAYOc2NtYkwHaayAx4bE12TuTOe1jX3mggpXKLhMoDixeomO4AUrTcX9XFP2UAUl96bBX-sbQmpZQ7Aqy9Q2f4MZZUc4LTksPgL9i5JtNsi6xSRH-GK9BC8RDjz8iih51LSkYfEj2aS_W9sOOdNiztWw7E3i0hyRld-f2bHxxIULepnkirx1aa0rzANR15JE7rLh5aopgb30-rQ
 * http://algs4.cs.princeton.edu/code/
 * https://mail-attachment.googleusercontent.com/attachment/u/0/?ui=2&ik=26879a90be&view=att&th=144181d1dbd12052&attid=0.1&disp=inline&realattid=f_hrgp4bvu0&safe=1&zw&saddbat=ANGjdJ_JIBSxUhjXUIqeJ0jzNlA-BHjIGbRoerYHZzzbM2NZW4qF3Gpz2UWsx-X0yr39Od8wJPHyPZ2VaL3zEdwzALacEgmHfOYsWz1cQqq2efevk65cT4oxD2xHMb9oyHkHy0IrCoBH_R18XRC08fj6Q-4_D0Fej7Scy1FwLL8l3UJYdTWMtG4cFghJIsFJGxF-FiZurJ7Yc_3ZEj5dA1-KxoJe2pEufyZoDR93C6-VjA-Iw6D7iJfLsh8r4MO1v-dlwQbut9TWVM-ccv5hjxkaT1N96mM90DUiUQV4849iKRbG2x_4mxuLAEGqMizeh7eaTrfZtL8MYqTI-dDQ53i9tbfCesyTExnZyUmDlD_Vxnkq8Sl5NXwxf-VBznUoC-248OsGl43SesmR8Ck5H49iLEG6_h4wrP9jZvPNOK2qYzmmwIVjfimdnPrNOY331mLhXPGvQ_UsTUJ5AC4L2z5IdVGnrwa-moX91ib6znpu7TNmf4yheAZeZiAixXKxmQYNKXlsuiDlYJFOlRGrgglk-y6hOqLIfXwYxGrY8zi7lxTS3Ggr-1nOUox14WwoCftRhiJ86XtwG1vNFC7ll6eO9qgIrDbkJQ7-tf1BTHsgxwf1NVXLw3-qUjJm4QA1RQerQkJnGhXw3mELyCvNrZ3V2QabCsHvEEuBlVAE8w
 * https://www.khanacademy.org/computing/computer-science/algorithms
 * http://fr.wikihow.com/gagner-une-partie-de-Risk
 * StarCraft
 */
class Player {

    static int currentTurnNumber = 1;

    /**
     * todo : chercher dans l'annuaire
     *
     * @return the full Edge or null if not found
     */
    public static Edge getEdge(List<Edge> edges, int factoryId_A, int factoryId_B) {
        return edges.stream()
                .filter(edge -> (edge.getFactoryId_A() == factoryId_A && edge.getFactoryId_B() == factoryId_B)
                        ||
                        (edge.getFactoryId_A() == factoryId_B && edge.getFactoryId_B() == factoryId_A)
                )
                .findFirst().orElse(null);
    }

    /**
     * todo : chercher dans l'annuaire
     *
     * @return the asked Factory or null if not found
     */
    public static Factory getFactory(List<Factory> factories, int id) {
        return factories.stream()
                .filter(factory -> factory.getId() == id)
                .findAny().orElse(null);
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int factoryCount = in.nextInt(); // the number of factories
        List<Factory> factories = new ArrayList<>(factoryCount);
        for (int id = 0; id < factoryCount; id++) {
            Factory factory = new Factory(id);
            factories.add(factory);
        }

        int linkCount = in.nextInt(); // the number of links between factories
        List<Edge> edges = new ArrayList<>(linkCount);
        for (int i = 0; i < linkCount; i++) {
            int factory1 = in.nextInt();
            int factory2 = in.nextInt();
            int distance = in.nextInt();

            Edge edge = new Edge(factory1, factory2, distance);
            edges.add(edge);
        }

        List<Bomb> bombs = new ArrayList<>(4);
        // game loop
        while (true) {
            int entityCount = in.nextInt(); // the number of entities (e.g. factories and troops)
            for (int i = 0; i < entityCount; i++) {
                int entityId = in.nextInt();
                String entityType = in.next();
                int arg1 = in.nextInt();
                Owner owner = Owner.get(arg1);

                int arg2 = in.nextInt();
                int arg3 = in.nextInt();
                int arg4 = in.nextInt();
                int arg5 = in.nextInt();
                switch (entityType) {
                    case "FACTORY":
                        int nbOfCyborgsInFactory = arg2;
                        int production = arg3;
                        int remainingUnproductiveTurns = arg4;
                        Factory factory = getFactory(factories, entityId);
                        if (factory != null) {
                            factory.setOwner(owner);
                            factory.setStockOfCyborgs(nbOfCyborgsInFactory);
                            factory.setProduction(production);
                            factory.setRemainingUnproductiveTurns(remainingUnproductiveTurns);
                        }
                        break;
                    case "TROOP":
                        int factoryIdSource = arg2;
                        int factoryIdDestination = arg3;
                        Edge edge = getEdge(edges, factoryIdSource, factoryIdDestination);

                        int nbOfCyborgsInTroop = arg4;
                        int remainingDistance = arg5;

                        Troop troop = new Troop(entityId, owner, nbOfCyborgsInTroop, remainingDistance, factoryIdDestination);
                        if (edge != null) {
                            edge.addTroop(troop);
                        } else {
                            System.err.println("IMPOSSIBLE ERROR : NO EDGE FOUND (no hedge fund...)");
                        }
                        break;
                    case "BOMB":
                        int sourceFactoryId = arg2;
                        int targetFactoryId = arg3;
                        int countdown = arg4;
                        Bomb bomb = new Bomb(entityId, owner, sourceFactoryId, targetFactoryId, countdown);
                        bombs.add(bomb);
                        break;
                    default:
                        break;
                }

            }

            boolean finishToComputeDangerScore = computeAllDangerScore(factories, edges);
            computeOpportunityScore(factories, edges, finishToComputeDangerScore);

            takeADecision(factories, edges, bombs);
            clearTroopsFrom(edges);
            factories.stream().forEach(factory -> factory.setSentABomb(false));
            currentTurnNumber++;
        }
    }

    private static void clearTroopsFrom(List<Edge> edges) {
        edges.stream().forEach(edge -> edge.clearTroops());
    }

    /**
     * MUST BE CALLED AFTER computeDangerScore <br/>
     * based on : <br/>
     * FOR ENEMY   production [divide by] distance from ally + danger score <br/>
     * FOR NEUTRAL production + distance from enemy [divide by] danger score <br/>
     * the bigger the better !
     */
    public static void computeOpportunityScore(List<Factory> factories, List<Edge> edges, boolean isDangerScoreComputed) {

        List<Factory> enemiesFactories = factories.stream()
                .filter(factory1 -> factory1.getOwner() == Owner.enemy).collect(Collectors.toList());
        for (Factory enemyFactory : enemiesFactories) {
            computeEnemyOpportunityScore(factories, edges, enemyFactory, isDangerScoreComputed);
        }

        List<Factory> neutralFactories = factories.stream()
                .filter(factory1 -> factory1.getOwner() == Owner.neutral).collect(Collectors.toList());
        for (Factory neutralFactory : neutralFactories) {
            computeNeutralOpportunityScore(factories, edges, neutralFactory, isDangerScoreComputed);
        }

        List<Factory> allyFactories = factories.stream()
                .filter(factory1 -> factory1.getOwner() == Owner.ally).collect(Collectors.toList());
        for (Factory allyFactory : allyFactories) {
            computeAllyOpportunityScore(factories, edges, allyFactory, isDangerScoreComputed);
        }
    }

    /**
     * todo : étudier
     * permet de choisir la meilleure factory alliée pour increase
     * the bigger the better !
     */
    private static void computeAllyOpportunityScore(List<Factory> factories, List<Edge> edges, Factory allyFactory, boolean isDangerScoreComputed) {
        if (isDangerScoreComputed) {
            float sumScore = 0;
            List<Factory> enemyNeighbors = getEnemyNeighbors(factories, edges, allyFactory);
            for (Factory enemyFactory : enemyNeighbors) {
                Edge edge = getEdge(edges, allyFactory.getId(), enemyFactory.getId());
                if (edge != null) {
                    int production = allyFactory.getProduction();
                    // on ignore les factory qu'on ne peut plus increase dans la méthode d'increase
                    float dangerScore = allyFactory.getDangerScore() == 0 ? 1 : allyFactory.getDangerScore();
                    float score = ((float) (production + edge.getDistance())) / dangerScore;
                    sumScore += score;
                }
            }
            float averageScore = sumScore / enemyNeighbors.size();
            allyFactory.setOpportunityScore(averageScore);
        } else {
            System.err.println("IMPOSSIBLE ERROR : DANGER SCORE HAS NOT BEEN COMPUTED");
        }
    }

    /**
     * permet de choisir la meilleure factory neutre la plus éloignée de l'ennemi
     * the bigger the better !
     */
    private static void computeNeutralOpportunityScore(List<Factory> factories, List<Edge> edges, Factory neutralFactory, boolean isDangerScoreComputed) {
        if (isDangerScoreComputed) {
            float sumScore = 0;
            List<Factory> enemyNeighbors = getEnemyNeighbors(factories, edges, neutralFactory);
            for (Factory enemyFactory : enemyNeighbors) {
                Edge ennemyEdge = getEdge(edges, neutralFactory.getId(), enemyFactory.getId());
                if (ennemyEdge != null) {
                    float dangerScore = neutralFactory.getDangerScore() == 0 ?
                            1 : neutralFactory.getDangerScore();
                    float score = neutralFactory.getProduction() == 0 ?
                            0 : ((float) (neutralFactory.getProduction() + ennemyEdge.getDistance())) / dangerScore;
                    sumScore += score;
                }
            }
            float averageScore = sumScore / enemyNeighbors.size();
            neutralFactory.setOpportunityScore(averageScore);
        } else {
            System.err.println("IMPOSSIBLE ERROR : DANGER SCORE HAS NOT BEEN COMPUTED");
        }
    }

    /**
     * permet de choisir le "meilleur" enemy à attaquer
     * big is better
     */
    private static void computeEnemyOpportunityScore(List<Factory> factories, List<Edge> edges, Factory enemyFactory, boolean isDangerScoreComputed) {
        if (isDangerScoreComputed) {
            List<Factory> allyNeighbors = getAllyNeighbors(factories, edges, enemyFactory);
            float sumScore = 0;
            for (Factory allyFactory : allyNeighbors) {
                Edge edge = getEdge(edges, enemyFactory.getId(), allyFactory.getId());
                if (edge != null) {
                    float score = (float) enemyFactory.getProduction() / (edge.getDistance() + enemyFactory.getDangerScore());
                    sumScore += score;
                }
            }
            float averageScore = sumScore / allyNeighbors.size();
            enemyFactory.setOpportunityScore(averageScore);
        } else {
            System.err.println("IMPOSSIBLE ERROR : DANGER SCORE HAS NOT BEEN COMPUTED");
        }
    }

    /**
     * todo chercher un binome
     */
    public static Factory getNearestAllyNeighbors(List<Factory> factories, List<Edge> edges, Factory factory) {
        List<Factory> allyNeighbors = getAllyNeighbors(factories, edges, factory);

        if (allyNeighbors.size() == 1) {
            return allyNeighbors.get(0);
        }

        int minDistance = 100000;
        Factory result = null;
        for (Factory allyNeighbor : allyNeighbors) {
            Edge edge = getEdge(edges, allyNeighbor.getId(), factory.getId());
            if (edge != null) {
                int distance = edge.getDistance();
                if (distance < minDistance) {
                    minDistance = distance;
                    result = allyNeighbor;
                }
            }
        }
        return result;
    }

    /**
     * todo : prendre des nouvelles
     */
    public static List<Factory> getAllyNeighbors(List<Factory> factories, List<Edge> edges, Factory factory) {
        List<Factory> allNeighbors = getNeighbors(factories, edges, factory);
        return allNeighbors.stream().filter(factory1 -> factory1.getOwner() == Owner.ally).collect(Collectors.toList());
    }

    /**
     *
     * */
    public static List<Factory> getEnemyNeighbors(List<Factory> factories, List<Edge> edges, Factory factory) {
        List<Factory> allNeighbors = getNeighbors(factories, edges, factory);
        return allNeighbors.stream().filter(factory1 -> factory1.getOwner() == Owner.enemy).collect(Collectors.toList());
    }

    /**
     * todo chercher les voisins
     */
    private static List<Factory> getNeighbors(List<Factory> factories, List<Edge> edges, Factory factory) {
        /*
         * garder les edges qui ont la factory sur une extrémité
         * puis récupérer les factories de ces edges != factory de référence
         * */
        List<Edge> edgeNeighbors = getConnectedEdges(factory, edges);
        List<Factory> allNeighbors = new ArrayList<>();
        for (Edge edge : edgeNeighbors) {
            allNeighbors.add(
                    getFactory(
                            factories,
                            edge.getFactoryId_A() != factory.getId() ?
                                    edge.getFactoryId_A() : edge.getFactoryId_B())
            );
        }
        return allNeighbors;
    }

    /**
     * todo : étudier
     * calculate the number of cyborgs needed to defend (ally) or to conquer (enemy) a factory <br/>
     * based on : <br/>
     * incoming hostile troops <br/>
     * incoming ally troops <br/>
     * troop Remaining Distance
     */
    public static boolean computeAllDangerScore(List<Factory> factories, List<Edge> edges) {
        for (Factory factory : factories) {
            computeDangerScore(factory, edges);
        }
        return true;
    }

    /**
     * todo : étudier
     * calculate the number of cyborgs needed to defend (ally) or to conquer (enemy) a factory <br/>
     * based on : <br/>
     * incoming hostile troops <br/>
     * incoming ally troops <br/>
     * troop Remaining Distance
     */
    private static boolean computeDangerScore(Factory factory, List<Edge> edges) {
        float score = factory.getOwner() == Owner.ally ?
                factory.getStockOfCyborgs() * -1 : factory.getStockOfCyborgs();
        List<Edge> connectedEdge = getConnectedEdges(factory, edges);
        for (Edge edge : connectedEdge) {
            List<Troop> hostileTroops = edge.getTroops().stream()
                    .filter(troop -> troop.getOwner() != Owner.ally
                            && troop.getDestinationFactoryId() == factory.getId()
                            && troop.getRemainingDistance() != 0
                    )
                    .collect(Collectors.toList());
            for (Troop troop : hostileTroops) {
                score += troop.getNbOfCyborgs() / troop.getRemainingDistance();
                if (factory.getOwner() == Owner.ally && !factory.isSpreadAllowed()) {
                    score -= factory.getProduction() * troop.getRemainingDistance();
                }
            }

            List<Troop> allyTroops = edge.getTroops().stream()
                    .filter(troop -> troop.getOwner() == Owner.ally
                            && troop.getDestinationFactoryId() == factory.getId()
                            && troop.getRemainingDistance() != 0)
                    .collect(Collectors.toList());
            for (Troop troop : allyTroops) {
                score -= troop.getNbOfCyborgs() / troop.getRemainingDistance();
            }
        }
        factory.setDangerScore(score);
        return true;
    }

    /**
     * Any valid action, such as "WAIT" or "MOVE source destination cyborgs" <br/>
     * based on production & distance
     * <p>
     * One game turn is computed as follows:
     * <p>
     * Move existing troops and bombs
     * Execute user orders
     * Produce new cyborgs in all factories
     * Solve battles
     * Make the bombs explode
     * Check end conditions
     */
    public static void takeADecision(List<Factory> factories, List<Edge> edges, List<Bomb> bombs) {
        StringBuffer action = new StringBuffer();

        if (currentTurnNumber == 1) {
            spreadNeutral(factories, edges, action);
        } else {
            List<Factory> allies = factories.stream()
                    .filter(factory -> factory.getOwner() == Owner.ally)
                    .sorted((o1, o2) -> sortByDangerScoreDesc(o1, o2))
                    .collect(Collectors.toList());
            counterMeasure(factories, edges, allies, action); // 2 façons de défendre : mitigate et runaway
            // todo se "défendre" contre bombes ennemies : kernel panic, aller sur des longs chemins
            // todo juste avant d'envoyer les bombes, vérifier si une à nous est en cours d'envoie puis lancer une troop à sa suite
            // si on envoie 2 bombes au même endroit attendre au moins le retour à la normal de la victime avant d'envoyer la bombe sur la meme cible
            //sendOneBomb(factories, edges, bombs, action); // always first
            sendAllBombs(factories, edges, bombs, action);

            List<Factory> optimized = factories.stream()
                    .sorted((o1, o2) -> sortByOpportunityDesc(o1, o2))
                    .collect(Collectors.toList());
            //optimized.forEach(factory -> System.err.println("takeADecision opportunity desc : " + factory.toString()));
            // todo : planquer des réserves de cyborg sur les productions <= 1 (et ne pas increase dessus)
            for (Factory factory : optimized) {
                switch (factory.getOwner()) {
                    case ally:
                        increaseProduction(factories, edges, action);
                        break;
                    case neutral:
                    case enemy:
                        //assault(factories, edges, factory, action);
                        break;
                }
            }
        }
        System.out.println(action.toString() + "MSG soli Deo gloria");
    }

    private static void sendOneBomb(List<Factory> factories, List<Edge> edges, List<Bomb> bombs, StringBuffer action) {
        List<Bomb> usedBombs = bombs.stream()
                .filter(bomb -> bomb.getOwner() == Owner.ally)
                .collect(Collectors.toList());
        if (usedBombs.size() < 2) {
            Factory target = factories.stream()
                    .filter(factory -> factory.getOwner() == Owner.enemy && factory.getProduction() >= 2)
                    .sorted((o1, o2) -> o2.getProduction() - o1.getProduction())
                    .sorted((o1, o2) -> sortByDangerScoreDesc(o1, o2))
                    .findFirst().orElse(null);
            if (target != null) {
                int source = getNearestAllyNeighbors(factories, edges, target)
                        .getId();
                int destination = target.getId();
                action.append("BOMB " + source + " " + destination + ";");
                // target.setBombing(); todo : indiquer qu'une factory est en train d'être bombardée et donc ne pas la prendre pour cible
                // todo : retarder cette action ? (la faire avec la même logique d'attente que l'escouade qui suit la bombe)
                //increaseProduction(factories, edges, action);
            }
        }
    }

    /**
     * old school !
     */
    private static void sendAllBombs(List<Factory> factories, List<Edge> edges, List<Bomb> bombs, StringBuffer action) {
        List<Bomb> usedBombs = bombs.stream()
                .filter(bomb -> bomb.getOwner() == Owner.ally)
                .collect(Collectors.toList());
        if (usedBombs.size() == 0) {
            List<Factory> targets = factories.stream()
                    .filter(factory -> factory.getOwner() == Owner.enemy && factory.getProduction() >= 2)
                    .sorted((o1, o2) -> o2.getProduction() - o1.getProduction())
                    .sorted((o1, o2) -> Math.round(o2.getDangerScore()) - Math.round(o1.getDangerScore()))
                    .collect(Collectors.toList());
            if (targets.size() >= 2) {
                sendBomb(factories, edges, targets.get(0), action);
                sendBomb(factories, edges, targets.get(1), action);
            }
        }
    }

    private static void sendBomb(List<Factory> factories, List<Edge> edges, Factory target, StringBuffer action) {
        Factory from = getNearestAllyNeighbors(factories, edges, target);
        from.setSentABomb(true);
        int source = from.getId();
        int destination = target.getId();
        action.append("BOMB " + source + " " + destination + ";");
        System.err.println("sending a bomb from factory " + source + " to factory " + destination);
    }

    /**
     * todo : prier, se resourcer
     */
    private static void increaseProduction(List<Factory> factories, List<Edge> edges, StringBuffer action) {
        // int increaseSpeed = 1;
        // on peut faire dans cet ordre : 30,20,10
        List<Factory> increaseReady = factories.stream()
                .filter(factory -> factory.getOwner() == Owner.ally
                        && factory.getStockOfCyborgs() > factory.getDangerScore() + 10
                        && factory.getProduction() < 3
                        && factory.getRemainingUnproductiveTurns() == 0
                )
                .sorted((o1, o2) -> sortByOpportunityDesc(o1, o2))
                .collect(Collectors.toList());
        for (Factory provider : increaseReady) {
            System.err.println("increase production for factory " + provider.getId());
            action.append("INC " + provider.getId() + ";");
            provider.setStockOfCyborgs(provider.getStockOfCyborgs() - 10);
            provider.setProduction(provider.getProduction() + 1);
            boolean isDangerScoreComputed = computeDangerScore(provider, edges);
            computeAllyOpportunityScore(factories, edges, provider, isDangerScoreComputed);
        }
    }

    private static int sortByOpportunityDesc(Factory o1, Factory o2) {
        return Math.round(o2.getOpportunityScore() - o1.getOpportunityScore());
    }

    /**
     * todo : encourager ou fuir
     */
    private static void counterMeasure(List<Factory> factories, List<Edge> edges, List<Factory> toDefend, StringBuffer action) {
        // defensive
        for (Factory target : toDefend) {
            if (target.getDangerScore() > 0) {
                System.err.println("mitigate factory : " + target.getId() + ". Danger score : " + target.getDangerScore());
                mitigate(factories, edges, target, action);
            } else {
                target.setSpreadAllowed(true);
            }
        }
    }

    /**
     * todo : encourager
     * défense à l'ancienne : envoyer des renforts si on a la capex, sinon fuir
     * todo : défendre par anticipation en laissant exprès des cyborgs sur les grosses productions alliées
     */
    static void mitigate(List<Factory> factories, List<Edge> edges, Factory toDefend, StringBuffer action) {
        List<Factory> allyNeighbors = getAllyNeighbors(factories, edges, toDefend);
        allyNeighbors = allyNeighbors.stream()
                .filter(factory -> factory.isSpreadAllowed() && !factory.sentABomb())
                .collect(Collectors.toList());

        int totalArmy = 0;
        for (Factory ally : allyNeighbors) {
            int localArmy = getLocalArmy(ally);
            totalArmy += localArmy;
        }
        if (totalArmy > toDefend.getDangerScore()) {
            System.err.println("sending backup to factory " + toDefend.getId());
            toDefend.setSpreadAllowed(false);
            for (Factory source : allyNeighbors) {
                int localArmy = getLocalArmy(source);
                action.append(move(source.getId(), toDefend.getId(), localArmy, factories, edges));
            }
        } else {
            System.err.println("they are too powerful ! give up factory " + toDefend.getId());
            Factory lostFactory = toDefend;
            runAway(factories, edges, lostFactory, action);
            // todo : send a single bomb on the lost factory : estimate time of arrival of enemy troop
        }
    }

    /**
     * todo : fuir la persécution
     * défense militaire à la Didier : fuir. en laissant une petite troupe pour affaiblir l'ennemi
     */
    static void runAway(List<Factory> factories, List<Edge> edges, Factory lostFactory, StringBuffer action) {
        lostFactory.setSpreadAllowed(true);
        List<Factory> shelters = factories.stream()
                .filter(factory -> factory.getOwner() == Owner.ally)
                .sorted((o1, o2) -> Math.round(o1.getDangerScore() - o2.getDangerScore()))
                .collect(Collectors.toList());

        for (Factory shelter : shelters) {
            // todo : la répartition homogène n'est pas forcément la meilleur solution
            int nbOfCyborgs = (lostFactory.getStockOfCyborgs() / shelters.size());
            action.append(move(lostFactory.getId(), shelter.getId(), nbOfCyborgs, factories, edges));
        }
    }

    private static int sortByDangerScoreDesc(Factory o1, Factory o2) {
        return Math.round(o2.getDangerScore() - o1.getDangerScore());
    }

    /**
     * todo : évangéliser
     */
    private static void spreadNeutral(List<Factory> factories, List<Edge> edges, StringBuffer action) {
        List<Factory> allies = factories.stream()
                .filter(factory -> factory.getOwner() == Owner.ally && factory.isSpreadAllowed() && !factory.sentABomb())
                .sorted((o1, o2) -> o2.getStockOfCyborgs() - o1.getStockOfCyborgs())
                .collect(Collectors.toList());
        Factory ally = allies.get(0);

        List<Factory> toConquer = factories.stream()
                .filter(factory -> factory.getOwner() == Owner.neutral)
                .sorted((o1, o2) -> sortByOpportunityDesc(o1, o2))
                .collect(Collectors.toList());
        toConquer.forEach(factory -> System.err.println("spreadNeutral OpportunityDesc : " + factory.toString()));
        //for (Factory ally : allies) {
        // todo : se limiter sur le nombre de factory à conquérir ?
        // oui, ne pas dépasser la frontière médiane == prendre Math.round(toConquer.size()/2)
        // et répartir le reste des troop de façon uniform
        //*
        int remainingCyborgs = ally.getStockOfCyborgs();
        for (int index = 0; index < Math.round(toConquer.size() / 2); index++) {
            Factory target = toConquer.get(0);
            int nbOfCyborgs = getNbOfCyborgsForConquest(ally, target);
            remainingCyborgs -= nbOfCyborgs;
        }
        //*/
        for (int index = 0; index < Math.round(toConquer.size() / 2); index++) {
            Factory target = toConquer.get(index);
            int nbOfCyborgs = getNbOfCyborgsForConquest(ally, target) + Math.round((float) remainingCyborgs / (float) toConquer.size());
            //int nbOfCyborgs = Math.round(target.getDangerScore()) + 1;
            System.err.println("[spreadNeutral] planned nbOfCyborgs : " + nbOfCyborgs);
            int actualnbOfCyborgs = nbOfCyborgs > ally.getStockOfCyborgs() ? ally.getStockOfCyborgs() : nbOfCyborgs;
            System.err.println("[spreadNeutral] actual nbOfCyborgs : " + actualnbOfCyborgs);
            action.append(move(ally.getId(), target.getId(), actualnbOfCyborgs, factories, edges));
        }
        //}
    }

    private static int getNbOfCyborgsForConquest(Factory ally, Factory target) {
        int nbOfCyborgs = Math.round(target.getDangerScore()) + 1;
        nbOfCyborgs = nbOfCyborgs > ally.getStockOfCyborgs() ? ally.getStockOfCyborgs() : nbOfCyborgs;
        return nbOfCyborgs;
    }

    /**
     * todo : convaincre
     * deadly only after spreadNeutral
     * pour une cible donnée, sélectionner les voisins alliés (proches) dont la somme des cyborgs > score danger de cible
     * puis lancer l'assaut !
     */
    private static void assault(List<Factory> factories, List<Edge> edges, Factory target, StringBuffer action) {
        List<Factory> allyNeighbors = getAllyNeighbors(factories, edges, target);
        allyNeighbors = allyNeighbors.stream()
                .filter(factory -> factory.isSpreadAllowed() && !factory.sentABomb())
                .collect(Collectors.toList());

        int totalArmy = 0;
        for (Factory source : allyNeighbors) {
            int localArmy = getLocalArmy(source);
            totalArmy += localArmy;
        }
        if (totalArmy > target.getDangerScore()) {
            System.err.println("Assault factory " + target.getId());
            for (Factory source : allyNeighbors) {
                int localArmy = getLocalArmy(source);
                action.append(move(source.getId(), target.getId(), localArmy, factories, edges));
            }
        } else {
            action.append("WAIT;");
        }
    }

    private static int getLocalArmy(Factory source) {
        int localArmy = Math.round((float) source.getStockOfCyborgs() - source.getDangerScore());
        localArmy = localArmy < 0 ? 0 : localArmy;
        return localArmy;
    }

    /**
     * todo : prendre des nouvelles
     */
    public static List<Edge> getConnectedEdges(Factory factory, List<Edge> edges) {
        return edges.stream()
                .filter(edge -> edge.getFactoryId_A() == factory.getId() || edge.getFactoryId_B() == factory.getId())
                .collect(Collectors.toList());
    }

    /**
     * todo : agir
     */
    public static String move(int source, int destination, int nbOfCyborgs, List<Factory> factories, List<Edge> edges) {
        if (nbOfCyborgs <= 0 || source == destination) {
            return "WAIT;";
        } else {
            // mettre à jour les stats
            Factory from = factories.stream().filter(factory -> factory.getId() == source).findFirst().orElse(null);
            if (from != null) {
                from.setStockOfCyborgs(from.getStockOfCyborgs() - nbOfCyborgs);
            }
            Factory to = factories.stream().filter(factory -> factory.getId() == destination).findFirst().orElse(null);
            if (to != null) {
                to.setStockOfCyborgs(to.getStockOfCyborgs() + nbOfCyborgs);
            }
            // maj les indicateurs
            Factory target = factories.stream()
                    .filter(factory -> factory.getId() == destination)
                    .findFirst().orElse(null);
            if (target != null) {
                boolean isDangerScoreComputed = computeDangerScore(target, edges);
                switch (target.getOwner()) {
                    case enemy:
                        computeEnemyOpportunityScore(factories, edges, target, isDangerScoreComputed);
                        break;
                    case neutral:
                        computeNeutralOpportunityScore(factories, edges, target, isDangerScoreComputed);
                        break;
                    case ally:
                        computeAllyOpportunityScore(factories, edges, target, isDangerScoreComputed);
                        break;
                }
            }
            // communiquer l'action
            return "MOVE " + source + " " + destination + " " + nbOfCyborgs + ";";
        }
    }
}

class Edge {

    private final int factoryId_A;
    private final int factoryId_B;
    private final int distance;

    private List<Troop> troops = new ArrayList<>();

    public Edge(int factoryId_A, int factoryId_B, int distance) {
        this.factoryId_A = factoryId_A;
        this.factoryId_B = factoryId_B;
        this.distance = distance;
    }

    /**
     * creates partial Edge with empty troops and invalid distance(-1)
     */
    public Edge(int factoryId_A, int factoryId_B) {
        this.factoryId_A = factoryId_A;
        this.factoryId_B = factoryId_B;
        this.distance = -1;
    }

    public void addTroop(Troop troop) {
        troops.add(troop);
    }

    public void clearTroops() {
        troops.clear();
    }

    /**
     * @return a copy of troops
     */
    public List<Troop> getTroops() {
        return new ArrayList<>(troops);
    }

    public int getFactoryId_A() {
        return factoryId_A;
    }

    public int getFactoryId_B() {
        return factoryId_B;
    }

    public int getDistance() {
        return distance;
    }

    /**
     * Edges are given with an orientation (from-to) BUT troops can move both ways
     * so this method tests only the facories' id in both ways
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return (factoryId_A == edge.factoryId_A &&
                factoryId_B == edge.factoryId_B) ||
                (factoryId_A == edge.factoryId_B &&
                        factoryId_B == edge.factoryId_A);
    }

    @Override
    public int hashCode() {
        return Objects.hash(factoryId_A, factoryId_B);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "factoryId_A=" + factoryId_A +
                ", factoryId_B=" + factoryId_B +
                ", distance=" + distance +
                '}';
    }
}

class Factory {
    final private int id;

    private Owner owner;
    private int stockOfCyborgs;
    /**
     * Each turn, every non-neutral factory produces between 0 and 3 cyborgs.
     * BUT once the factory is created, this number will not change !
     */
    private int production = 0;
    /**
     * used to calculate the opportunity score of this factory when this.owner != ally <br/>
     * a high score means good opportunity
     */
    private float opportunityScore = 0;

    /**
     * used to calculate the danger score of this factory <br/>
     * a high score means high danger. low score means safe. can be negative (low)
     */
    private float dangerScore = 0;

    private int remainingUnproductiveTurns;

    private boolean sentABomb = false;

    private boolean spreadAllowed = true;

    public boolean isSpreadAllowed() {
        return spreadAllowed;
    }

    public void setSentABomb(boolean sentABomb) {
        this.sentABomb = sentABomb;
    }

    public boolean sentABomb() {
        return this.sentABomb;
    }

    public int getRemainingUnproductiveTurns() {
        return remainingUnproductiveTurns;
    }

    public void setRemainingUnproductiveTurns(int remainingUnproductiveTurns) {
        this.remainingUnproductiveTurns = remainingUnproductiveTurns;
    }

    public void setSpreadAllowed(boolean spreadAllowed) {
        this.spreadAllowed = spreadAllowed;
    }

    public int getProduction() {
        return production;
    }

    public void setProduction(int production) {
        this.production = production;
    }

    public Factory(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getStockOfCyborgs() {
        return stockOfCyborgs;
    }

    public void setStockOfCyborgs(int stockOfCyborgs) {
        this.stockOfCyborgs = stockOfCyborgs;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     * MUST BE CALLED AFTER computeDangerScore <br/>
     * based on : <br/>
     * FOR ENEMY   production [divide by] distance from ally + danger score <br/>
     * FOR NEUTRAL production + distance from enemy [divide by] danger score <br/>
     * the bigger the better !
     */
    public float getOpportunityScore() {
        return opportunityScore;
    }

    /**
     * MUST BE CALLED AFTER computeAllDangerScore <br/>
     * based on : <br/>
     * FOR ENEMY   production [divide by] distance from ally + danger score <br/>
     * FOR NEUTRAL production + distance from enemy [divide by] danger score <br/>
     * the bigger the better !
     */
    public void setOpportunityScore(float opportunityScore) {
        this.opportunityScore = opportunityScore;
    }

    /**
     * calculate the number of cyborgs needed to defend (ally) or to conquer (enemy) a factory <br/>
     * based on : <br/>
     * incoming hostile troops <br/>
     * incoming ally troops <br/>
     * troop Remaining Distance
     */
    public float getDangerScore() {
        return dangerScore;
    }

    /**
     * calculate the number of cyborgs needed to defend (ally) or to conquer (enemy) a factory <br/>
     * based on : <br/>
     * incoming hostile troops <br/>
     * incoming ally troops <br/>
     * troop Remaining Distance
     */
    public void setDangerScore(float dangerScore) {
        this.dangerScore = dangerScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factory factory = (Factory) o;
        return id == factory.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Factory{" +
                "id=" + id +
                ", owner=" + owner +
                ", stockOfCyborgs=" + stockOfCyborgs +
                ", production=" + production +
                ", opportunityScore=" + opportunityScore +
                ", dangerScore=" + dangerScore +
                ", remainingUnproductiveTurns=" + remainingUnproductiveTurns +
                ", sentABomb=" + sentABomb +
                ", spreadAllowed=" + spreadAllowed +
                '}';
    }
}

class Troop {
    final private int id;
    final private Owner owner;
    final private int nbOfCyborgs;
    final private int destinationFactoryId;

    private int remainingDistance;

    public Troop(int id, Owner owner, int nbOfCyborgs, int remainingDistance, int destinationFactoryId) {
        this.id = id;
        this.owner = owner;
        this.nbOfCyborgs = nbOfCyborgs;
        this.remainingDistance = remainingDistance;
        this.destinationFactoryId = destinationFactoryId;
    }

    public int getDestinationFactoryId() {
        return destinationFactoryId;
    }

    public Owner getOwner() {
        return owner;
    }

    public int getId() {
        return id;
    }

    public int getRemainingDistance() {
        return remainingDistance;
    }

    public void setRemainingDistance(int remainingDistance) {
        this.remainingDistance = remainingDistance;
    }

    public int getNbOfCyborgs() {
        return nbOfCyborgs;
    }

    @Override
    public String toString() {
        return "Troop{" +
                "id=" + id +
                ", owner=" + owner +
                ", nbOfCyborgs=" + nbOfCyborgs +
                ", destinationFactoryId=" + destinationFactoryId +
                ", remainingDistance=" + remainingDistance +
                '}';
    }
}

enum Owner {
    ally(1), enemy(-1), neutral(0);

    final int id;

    Owner(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Owner get(int id) {
        for (Owner owner : values()) {
            if (owner.getId() == id) {
                return owner;
            }
        }
        return null;
    }
}

class Bomb {
    private int id;
    private Owner owner;
    private int sourceFactoryId;
    private int targetFactoryId;
    private int countdown;

    public Bomb(int id, Owner owner, int sourceFactoryId, int targetFactoryId, int countdown) {
        this.id = id;
        this.owner = owner;
        this.sourceFactoryId = sourceFactoryId;
        this.targetFactoryId = targetFactoryId;
        this.countdown = countdown;
    }

    public int getId() {
        return id;
    }

    public Owner getOwner() {
        return owner;
    }

    public int getSourceFactoryId() {
        return sourceFactoryId;
    }

    public int getTargetFactoryId() {
        return targetFactoryId;
    }

    public int getCountdown() {
        return countdown;
    }
}