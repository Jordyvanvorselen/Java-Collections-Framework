/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.*;

import java.util.HashMap;
import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Brian
 */
public class HuffmanKnoopUtilTest {
    public String data;
    public String data2;

    public HuffmanKnoopUtilTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        data = "bananen";
        data2 = "1001101101010";
    }

    @After
    public void tearDown() {
    }

    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testGetFrequentie() {
        System.out.println("getFrequentie");
        HashMap<String, Integer> expResult = new HashMap<String, Integer>();
        expResult.put("n", 3);
        expResult.put("a", 2);
        expResult.put("b", 1);
        expResult.put("e", 1);

        HashMap<String, Integer> result = HuffmanCodering.getFrequentie(data);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetPriorityFrequentie() {
        HashMap<String, Integer> frequenties = HuffmanCodering.getFrequentie(data);
        PriorityQueue<HuffKnoop> priorityFrequentie = HuffmanCodering.sortFrequentie(frequenties);

        HuffKnoop h;
        h = priorityFrequentie.remove();
        assertEquals('b', h.getCharacter());

        h = priorityFrequentie.remove();
        assertEquals('e', h.getCharacter());

        h = priorityFrequentie.remove();
        assertEquals('a', h.getCharacter());

        h = priorityFrequentie.remove();
        assertEquals('n', h.getCharacter());


    }

    @Test
    public void testGetHuffmanBoom() {
        HashMap<String, Integer> frequenties = HuffmanCodering.getFrequentie(data);
        PriorityQueue<HuffKnoop> priorityFrequentie = HuffmanCodering.sortFrequentie(frequenties);
        HuffKnoop h = HuffmanCodering.createHuffmanTree(priorityFrequentie);
        assertNotNull(h);
        assertEquals('n', h.getLeftChild().getCharacter());
        assertEquals('a', h.getRightChild().getRightChild().getCharacter());
        assertEquals('e', h.getRightChild().getLeftChild().getRightChild().getCharacter());
        assertEquals('b', h.getRightChild().getLeftChild().getLeftChild().getCharacter());
    }

    @Test
    public void testGetCharacterCode() {
        //Faalt door niet perfecte boom.
        HashMap<String, Integer> frequenties = HuffmanCodering.getFrequentie(data);
        PriorityQueue<HuffKnoop> priorityFrequentie = HuffmanCodering.sortFrequentie(frequenties);
        HuffKnoop h = HuffmanCodering.createHuffmanTree(priorityFrequentie);

        HashMap<Character, String> expResult = new HashMap<>();
        expResult.put('n', "0");
        expResult.put('b', "100");
        expResult.put('e', "101");
        expResult.put('a', "11");

        HashMap<Character, String> result = HuffmanCodering.createCodes(h, "");
        assertEquals(expResult, result);
    }

    @Test
    public void testMostFrequentCharacterHasShortestCode() {
        HashMap<String, Integer> frequenties = HuffmanCodering.getFrequentie(data);
        PriorityQueue<HuffKnoop> priorityFrequentie = HuffmanCodering.sortFrequentie(frequenties);
        HuffKnoop h = HuffmanCodering.createHuffmanTree(priorityFrequentie);
        HashMap<Character, String> codes = HuffmanCodering.createCodes(h, "");

        boolean result = true;
        if (!(codes.get('n').length() < codes.get('a').length())) {
            result = false;
        }
        if (!(codes.get('a').length() < codes.get('e').length())) {
            result = false;
        }

        assertTrue(result);
    }

    @Test
    public void testGetCodedKnoop() {
        //Faalt door niet perfecte boom.
        HashMap<String, Integer> frequenties = HuffmanCodering.getFrequentie(data);
        PriorityQueue<HuffKnoop> priorityFrequentie = HuffmanCodering.sortFrequentie(frequenties);
        HuffKnoop h = HuffmanCodering.createHuffmanTree(priorityFrequentie);
        HashMap<Character, String> characterCodes = HuffmanCodering.createCodes(h, "");


        String expResult = "1001101101010";
        String result = HuffmanCodering.encode(characterCodes, data);
        assertEquals(expResult, result);

    }

    @Test
    public void testGetDecodedKnoop() {
        HashMap<String, Integer> frequenties = HuffmanCodering.getFrequentie(data);
        PriorityQueue<HuffKnoop> priorityFrequentie = HuffmanCodering.sortFrequentie(frequenties);
        HuffKnoop h = HuffmanCodering.createHuffmanTree(priorityFrequentie);

        String input = "1001101101010"; //Komt door foute boom, als we 1101001001110 (de Actual code van vorige unittest) invullen komt er wel het juiste uit. We hebben alleen geen perfecte boom.
        String expResult = "bananen";
        String result = HuffmanCodering.decode(input, h);
        assertEquals(expResult, result);
    }

    //Grote input
    @Test
    public void testFunctionalityWithLargeInput(){
        String message = "\n" +
                "Hef Bundy\n" +
                "Inbox 0\n" +
                "\n" +
                "    NwK\n" +
                "    Highest rated\n" +
                "    1.50\n" +
                "    Todays matches\n" +
                "    Results\n" +
                "    Demo section\n" +
                "    POV demo section\n" +
                "    Forum\n" +
                "    Search\n" +
                "    Galleries\n" +
                "    Gallery search\n" +
                "    Statistics\n" +
                "    Fan Center\n" +
                "    Movie section\n" +
                "    Upcoming events\n" +
                "    #1\n" +
                "    fnatic\n" +
                "    fnatic\n" +
                "    #2\n" +
                "    Natus Vincere\n" +
                "    Natus Vincere\n" +
                "    #3\n" +
                "    Astralis\n" +
                "    Astralis\n" +
                "    #4\n" +
                "    Luminosity\n" +
                "    Luminosity\n" +
                "    #5\n" +
                "    EnVyUs\n" +
                "    EnVyUs\n" +
                "    Complete ranking\n" +
                "    Last updated: 28th of March\n" +
                "    FACEIT League 2015 Stage 1 Finals - Day 1\n" +
                "    CS:GO s1mple vs NIP 1vs4 Clutch @ ESWC 2014\n" +
                "\n" +
                "    starstarstarstarinactivestar\n" +
                "\n" +
                "    Luminosity vs mousesports\n" +
                "    (276)\n" +
                "\n" +
                "    £12 on pick'ems\n" +
                "    (42)\n" +
                "\n" +
                "    IZAKOOO MLG\n" +
                "    (37)\n" +
                "\n" +
                "    Pasha tells about erection problems\n" +
                "    (0)\n" +
                "\n" +
                "    TS win Stream.me Gauntlet\n" +
                "    (70)\n" +
                "\n" +
                "    LIQUID WIN\n" +
                "    (9)\n" +
                "\n" +
                "    Vp will win over g2\n" +
                "    (14)\n" +
                "\n" +
                "    If Gambit win the Major\n" +
                "    (0)\n" +
                "\n" +
                "    FaZe vs Liquid\n" +
                "    (131)\n" +
                "\n" +
                "    NiP win the major\n" +
                "    (19)\n" +
                "\n" +
                "    IF G2 WINS THE MAJOR\n" +
                "    (11)\n" +
                "\n" +
                "    RATE THIS GIRL\n" +
                "    (56)\n" +
                "\n" +
                "    PROBLEM WITH CSGO, please check\n" +
                "    (25)\n" +
                "\n" +
                "    CSGL vs ALTERNATE aTTaX\n" +
                "    (6)\n" +
                "\n" +
                "    Natus Vincere vs Cloud9\n" +
                "    (89)\n" +
                "\n" +
                "    From Future AMA\n" +
                "    (0)\n" +
                "\n" +
                "    NiP vs FlipSid3\n" +
                "    (382)\n" +
                "\n" +
                "    UK CS EleGiggle\n" +
                "    (4)\n" +
                "\n" +
                "    MLG DAY 1 PREDICTIONS\n" +
                "    (32)\n" +
                "\n" +
                "    Sucides\n" +
                "    (65)\n" +
                "\n" +
                "    U S A U S A U S A!!!!\n" +
                "    (19)\n" +
                "\n" +
                "    NiP allu\n" +
                "    (32)\n" +
                "\n" +
                "    INSANE PRO MOMENTS #2 !\n" +
                "    (18)\n" +
                "\n" +
                "    47/54 odds ez\n" +
                "    (0)\n" +
                "\n" +
                "    Guess The Pro 2\n" +
                "    (44)\n" +
                "\n" +
                "    new topic\n" +
                "    SauceCSGO\n" +
                "    (77)\n" +
                "\n" +
                "    jobsie\n" +
                "    (218)\n" +
                "\n" +
                "    mlg_sw3g\n" +
                "    (15)\n" +
                "\n" +
                "    wtfuninstall\n" +
                "    (26)\n" +
                "\n" +
                "    neTneT\n" +
                "    (24)\n" +
                "\n" +
                "    Simpal\n" +
                "    (65)\n" +
                "\n" +
                "    wtfuninstall\n" +
                "    (40)\n" +
                "\n" +
                "    petr1k\n" +
                "    (22)\n" +
                "\n" +
                "    Perry89\n" +
                "    (90)\n" +
                "\n" +
                "    Heavon\n" +
                "    (106)\n" +
                "\n" +
                "    visomvet\n" +
                "    RAIDERS\n" +
                "    Ancient\n" +
                "    CSGL\n" +
                "    CPH Wolves\n" +
                "    CSGL\n" +
                "    Millenium\n" +
                "    LDLC Blue\n" +
                "    Tempo Storm\n" +
                "    Selfless\n" +
                "    TyLoo\n" +
                "    AG\n" +
                "    Wings\n" +
                "    HG\n" +
                "    visomvet\n" +
                "    RAIDERS\n" +
                "    Ancient\n" +
                "    CSGL\n" +
                "    CPH Wolves\n" +
                "    CSGL\n" +
                "    GuardiaN\n" +
                "    LDLC White\n" +
                "    pyth\n" +
                "    CLG\n" +
                "    XANTARES\n" +
                "    1337\n" +
                "    JW\n" +
                "    TSM\n" +
                "    JW\n" +
                "    TSM\n" +
                "\n" +
                "    NiP\n" +
                "    FlipSid3\n" +
                "    Luminosity\n" +
                "    mousesports\n" +
                "    CSGL\n" +
                "    ALTERNATE aTTaX\n" +
                "    FaZe\n" +
                "    Liquid\n" +
                "    fnatic\n" +
                "    Splyce\n" +
                "    EnVyUs\n" +
                "    CLG\n" +
                "    k1ck\n" +
                "    PENTA\n" +
                "    Astralis\n" +
                "    Gambit\n" +
                "    Natus Vincere\n" +
                "    Cloud9\n" +
                "    Virtus.pro\n" +
                "    G2\n" +
                "    [Caster] ESL TV\n" +
                "    (13538)\n" +
                "\n" +
                "    [Top player] ceh9\n" +
                "    (1303)\n" +
                "\n" +
                "    [Top player] Pimp\n" +
                "    (676)\n" +
                "\n" +
                "    [Top player] KAPARZO\n" +
                "    (344)\n" +
                "\n" +
                "    [Caster] ESL TV\n" +
                "    (261)\n" +
                "\n" +
                "    [Top player] KRAEUTERHUMPEN\n" +
                "    (145)\n" +
                "\n" +
                "    [Female player] Sindi\n" +
                "    (110)\n" +
                "\n" +
                "    [Top player] draken\n" +
                "    (88)\n" +
                "\n" +
                "    [Female player] Gina\n" +
                "    (86)\n" +
                "\n" +
                "    [Caster] PeanutGalleryTV\n" +
                "    (83)\n" +
                "\n" +
                "    [Caster] UCC\n" +
                "    (34)\n" +
                "\n" +
                "    [Counter-Strike: Global Offensive] BERRY\n" +
                "    (30)\n" +
                "\n" +
                "    [Female player] KLy\n" +
                "    (30)\n" +
                "\n" +
                "    [Top player] larsen\n" +
                "    (21)\n" +
                "\n" +
                "    [Top player] fredi\n" +
                "    (13)\n" +
                "\n" +
                "    [Top player] kaze\n" +
                "    (11)\n" +
                "\n" +
                "    [Caster] ESL TV (Hitbox)\n" +
                "    (6)\n" +
                "\n" +
                "    TyLoo\n" +
                "    2\n" +
                "    AllGamers\n" +
                "    0\n" +
                "    Wings\n" +
                "    2\n" +
                "    HG\n" +
                "    0\n" +
                "    Tempo Storm\n" +
                "    3\n" +
                "    Obey.Alliance\n" +
                "    1\n" +
                "    visomvet\n" +
                "    2\n" +
                "    RAIDERS\n" +
                "    0\n" +
                "    Ancient\n" +
                "    0\n" +
                "    CSGL\n" +
                "    2\n" +
                "    Empire\n" +
                "    1\n" +
                "    RCTIC\n" +
                "    2\n" +
                "    Alpha\n" +
                "    0\n" +
                "    Epsilon\n" +
                "    2\n" +
                "    CPH Wolves\n" +
                "    1\n" +
                "    CSGL\n" +
                "    2\n" +
                "    Absolute Monster\n" +
                "    2\n" +
                "    Signature\n" +
                "    16\n" +
                "    TheMongolz\n" +
                "    2\n" +
                "    MVP.karnal\n" +
                "    1\n" +
                "    VG.CyberZen\n" +
                "    30th of March.\n" +
                "    MVP project\n" +
                "    TyLoo\n" +
                "    30th of March.\n" +
                "    EDG\n" +
                "    AGG\n" +
                "    30th of March.\n" +
                "    Method\n" +
                "    E-frag.net\n" +
                "    FaZe\n" +
                "    Luminosity\n" +
                "    fnatic\n" +
                "    E-frag.net\n" +
                "    Tempo Storm\n" +
                "    Winterfox\n" +
                "    Luminosity\n" +
                "    SK\n" +
                "    NiP\n" +
                "\n" +
                "TS win Stream.me Gauntlet\n" +
                "By: stich\n" +
                "Time: 2016-03-29 06:45\n" +
                "Event: Stream.me Gauntlet\n" +
                "Game: Counter-Strike: Global Offensive\n" +
                "\n" +
                "Tempo Storm have won the Stream.me Gauntlet after defeating Obey.Alliance 3-1 (14-16 on de_mirage, 16-11 on de_cache, and 16-12 on de_train, with a one map advantage).\n" +
                "\n" +
                "The Stream.me Gauntlet was a $10,000 online tournament for North American teams that was meant to debut the eponymous new streaming platform on which the tournament is named.\n" +
                "\n" +
                "Although the event featured many teams under many readers' radars in its double elimination bracket, it did include the likes of Bee's Money Crew, KKona, Obey.Alliance, and finally the Brazilians of Tempo Storm who are the current 13th best team in the world and who have a knack for playing and winning these sorts of tournaments.\n" +
                "\n" +
                "\n" +
                "Tempo Storm win another regional tournament in North America\n" +
                "\n" +
                "The grand final of the event saw Henrique \"hen1\" Teles & co. going up against Obey.Alliance, a scrappy North American team to whom Tempo Storm have lost maps before in various tournaments (earlier in the Stream.me bracket as well as in GO:NA).\n" +
                "\n" +
                "Although Terry \"dsr\" Ryu and crew were able to continue their streak of playing the Brazilian team to close games by taking the first map of the series 16-14 (de_mirage), Tempo Storm rallied and closed out the final two maps of de_cache and de_train with 16-11 and 16-12 scorelines, therefore winning the best-of-five due to a one map upper bracket advantage.\n" +
                "\n" +
                "Result\n" +
                "Stream.me Gauntlet\n" +
                "Tempo Storm\n" +
                "3\n" +
                "Obey.Alliance\n" +
                "1\n" +
                "\n" +
                "Stream.me Gauntlet final standings:\n" +
                "\n" +
                "    1. Tempo Storm - $7,000\n" +
                "    2. Obey.Alliance - $2,000\n" +
                "    3. KKona - $1,000\n" +
                "\n" +
                "stich writes for HLTV.org and can be found on Twitter\n" +
                "syccc\n" +
                "#1\n" +
                "CONGRATZ\n" +
                "\n" +
                "Post edited 2016-03-29 06:45:30\n" +
                "2016-03-29 06:45:16\n" +
                "Sadee - <3 allu\n" +
                "#21\n" +
                "yes ENCE won!!\n" +
                "2016-03-29 07:04:41\n" +
                "FracturedButWhole2\n" +
                "#30\n" +
                "#FairPlay\n" +
                "2016-03-29 07:55:52\n" +
                "rooftopbomber\n" +
                "#53\n" +
                "xDDDDDDDDDDD\n" +
                "2016-03-29 08:58:19\n" +
                "BuddyINSANE\n" +
                "#23\n" +
                "CONGRATZ #TSWIN!\n" +
                "we smart, we loyal, we friendly, we are from BRAZIL!\n" +
                "2016-03-29 07:20:15\n" +
                "ghostcrendor\n" +
                "#54\n" +
                "but no major for ts :) still bad af\n" +
                "2016-03-29 09:23:44\n" +
                "narktastic\n" +
                "#63\n" +
                "Good\n" +
                "2016-03-29 12:02:43\n" +
                ".danNiX - <3 nex\n" +
                "#68\n" +
                "Yeah youve got really good cs-scene in the netherlands...\n" +
                "2016-03-29 13:33:41\n" +
                "DileBani - <3 jOELZ\n" +
                "#56\n" +
                "Kys\n" +
                "2016-03-29 09:48:00\n" +
                "GuckFreen\n" +
                "#58\n" +
                "kys\n" +
                "2016-03-29 10:27:03\n" +
                "mezy\n" +
                "#61\n" +
                "kys\n" +
                "2016-03-29 11:27:33\n" +
                "NipTop1NoHate - <3 NEO\n" +
                "#2\n" +
                "SECOND AGAIN KYS\n" +
                "\n" +
                "Post edited 2016-03-29 06:45:24\n" +
                "2016-03-29 06:45:18\n" +
                "IDKWhoIAm\n" +
                "#11\n" +
                "So fast...\n" +
                "2016-03-29 06:46:15\n" +
                "Warfororks\n" +
                "#20\n" +
                "not fast enough, it seems\n" +
                "2016-03-29 07:00:22\n" +
                "KLANS\n" +
                "#64\n" +
                "for 2 seconds [*]\n" +
                "2016-03-29 12:11:35\n" +
                "russian fakeflagger\n" +
                "#3\n" +
                "k\n" +
                "2016-03-29 06:45:19\n" +
                "simplehltvuser\n" +
                "#4\n" +
                "memed\n" +
                "2016-03-29 06:45:21\n" +
                "Hyzek\n" +
                "#24\n" +
                "LETS FUCKING GOOOOOOOOOOOOOOOOOOOOOOO\n" +
                "\n" +
                "WE SMART, WE LOYAL, WE FRIENDLY, WE ARE FROM BRAZIL!\n" +
                "2016-03-29 07:33:54\n" +
                "simplehltvuser\n" +
                "#25\n" +
                "wild favelas appearing\n" +
                "killing myself\n" +
                "2016-03-29 07:34:33\n" +
                "Hyzek\n" +
                "#26\n" +
                "DONT HATE US DUDE\n" +
                "\n" +
                "WE SMART, WE LOYAL, WE FRIENDLY, WE ARE FROM BRAZIL!\n" +
                "2016-03-29 07:35:06\n" +
                "DileBani - <3 jOELZ\n" +
                "#57\n" +
                "Fakeflagger. Drink bleach\n" +
                "2016-03-29 09:48:46\n" +
                "Swattax\n" +
                "#5\n" +
                "ONLINERS\n" +
                "2016-03-29 06:45:25\n" +
                "Whorehey\n" +
                "#43\n" +
                "debatable\n" +
                "2016-03-29 08:20:49\n" +
                "Mirekz - <3 HeatoN\n" +
                "#6\n" +
                "even cheating obey can't beat TS ayyy\n" +
                "\n" +
                "Post edited 2016-03-29 06:46:32\n" +
                "2016-03-29 06:45:26\n" +
                "chrisfps7\n" +
                "#32\n" +
                "Cheating hen1 >>>> non-cheating Obey :))))))\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "Kappa\n" +
                "2016-03-29 08:10:31\n" +
                "Mirekz - <3 HeatoN\n" +
                "#33\n" +
                "wtf are u on m8 ill fk u up\n" +
                "2016-03-29 08:11:06\n" +
                "chrisfps7\n" +
                "#35\n" +
                "What's a NiP fan gonna do, cry about pyth to me? Hahaha, pyth deserved for rekt.\n" +
                "2016-03-29 08:12:43\n" +
                "Mirekz - <3 HeatoN\n" +
                "#36\n" +
                "sorry,i'm just a singer who already blew his shot.\n" +
                "2016-03-29 08:13:07\n" +
                "chrisfps7\n" +
                "#37\n" +
                "WHERE'S THE MONEY HEATON?????\n" +
                "2016-03-29 08:14:47\n" +
                "Mirekz - <3 HeatoN\n" +
                "#38\n" +
                "U WOT? here's a chill stream https://www.twitch.tv/arteezy lets enjoy it togetha brotha\n" +
                "2016-03-29 08:15:21\n" +
                "chrisfps7\n" +
                "#39\n" +
                "i arent click that\n" +
                "2016-03-29 08:15:43\n" +
                "Mirekz - <3 HeatoN\n" +
                "#40\n" +
                "y not m8, don't u know our lord and savior rtz?\n" +
                "2016-03-29 08:16:53\n" +
                "chrisfps7\n" +
                "#41\n" +
                "No, I have to go to bed because I've got class in the morning :(\n" +
                "\n" +
                "Gnight and gg to TS :)\n" +
                "2016-03-29 08:17:36\n" +
                "Mirekz - <3 HeatoN\n" +
                "#42\n" +
                "night m8, i will miss you tho ;(\n" +
                "2016-03-29 08:19:06\n" +
                "chrisfps7\n" +
                "#44\n" +
                "https://yt3.ggpht.com/-E86uUNlscr4/AAAAAAAAAAI/AAA..\n" +
                "\n" +
                "It's just a .jpg so no keylogger I promise :)\n" +
                "2016-03-29 08:21:28\n" +
                "Mirekz - <3 HeatoN\n" +
                "#45\n" +
                "i trust u love <3 KappaFullPride\n" +
                "2016-03-29 08:22:11\n" +
                "Goodjobguys\n" +
                "#47\n" +
                "An example of what should happen on hltv.\n" +
                "2016-03-29 08:31:46\n" +
                "Mirekz - <3 HeatoN\n" +
                "#48\n" +
                "gay relationships?\n" +
                "2016-03-29 08:32:38\n" +
                "Goodjobguys\n" +
                "#49\n" +
                "Uhhhhhh sure?\n" +
                "2016-03-29 08:32:58\n" +
                "Mirekz - <3 HeatoN\n" +
                "#50\n" +
                "no homo tho\n" +
                "2016-03-29 08:33:29\n" +
                "Goodjobguys\n" +
                "#51\n" +
                "Definitly.\n" +
                "2016-03-29 08:33:43\n" +
                "FiffylaureN - <3 Fifflaren\n" +
                "#70\n" +
                "How hard can it be?!?\n" +
                "http://www.d-e-f-i-n-i-t-e-l-y.com/\n" +
                "2016-03-29 13:51:12\n" +
                "hartberg\n" +
                "#7\n" +
                "ez 7k lmao\n" +
                "2016-03-29 06:45:46\n" +
                "kiQed - <3 OCEAN\n" +
                "#8\n" +
                "meme king KKona comin in 3rd\n" +
                "2016-03-29 06:45:52\n" +
                "pmtnice\n" +
                "#9\n" +
                "n1\n" +
                "2016-03-29 06:45:53\n" +
                "MountainDew\n" +
                "#10\n" +
                "Fuck off\n" +
                "2016-03-29 06:45:59\n" +
                "swz\n" +
                "#12\n" +
                "Obey making so many dumb plays in 3v2 2v2 situations on massively crucial rounds. Could have easily won if literally 2-3 rounds were played more carefully. RIP\n" +
                "2016-03-29 06:47:07\n" +
                "TdKappa\n" +
                "#13\n" +
                "Kkkk\n" +
                "2016-03-29 06:47:07\n" +
                "Zergelin\n" +
                "#14\n" +
                "Ez 7 cents\n" +
                "2016-03-29 06:47:17\n" +
                "ITRADEMYHEARTFORALIVER\n" +
                "#15\n" +
                "at least tier5 na this motherfuckers can win\n" +
                "2016-03-29 06:48:56\n" +
                "SuperAfim\n" +
                "#16\n" +
                "Ezzz for Brazil, people was wondering why TS was playing bad recently (DH qualifier and Cevo champ), here is the answer = hiding strats and saving energy for Stream Me Glauntet, cya NA.\n" +
                "2016-03-29 06:50:49\n" +
                "psiiico - <3 FalleN\n" +
                "#17\n" +
                "xD LMAO\n" +
                "2016-03-29 06:52:43\n" +
                "Xantennn\n" +
                "#31\n" +
                "+1\n" +
                "2016-03-29 08:06:30\n" +
                "obi_wan_kobe\n" +
                "#18\n" +
                "They were saving strats for the one and only odeag\n" +
                "2016-03-29 06:56:46\n" +
                "chrisfps7\n" +
                "#34\n" +
                "cya lan\n" +
                "2016-03-29 08:11:10\n" +
                "SpeedOMike\n" +
                "#19\n" +
                "KKona PogChamp 4Head\n" +
                "2016-03-29 06:58:21\n" +
                "MarinhoLeo\n" +
                "#22\n" +
                "nice\n" +
                "2016-03-29 07:09:54\n" +
                "f4CS - <3 f0rest\n" +
                "#27\n" +
                "tier999 tournament\n" +
                "2016-03-29 07:35:55\n" +
                "hontestly\n" +
                "#28\n" +
                "kkona in pro league xd\n" +
                "2016-03-29 07:37:02\n" +
                "TheAlzonian - <3 Skadoodle\n" +
                "#29\n" +
                "It speaks volumes about the skill level of this tournament when KKona are able to place 3rd.\n" +
                "2016-03-29 07:40:55\n" +
                "BuddyINSANE\n" +
                "#46\n" +
                "Congratz BOYS!\n" +
                "2016-03-29 08:29:59\n" +
                "MagaretoELf - <3 Tsogoo\n" +
                "#52\n" +
                "Mitio e pedal\n" +
                "2016-03-29 08:41:30\n" +
                "Analord01 - Happy fan club\n" +
                "#55\n" +
                "stream me daddy\n" +
                "2016-03-29 09:44:58\n" +
                "Lampard8\n" +
                "#59\n" +
                "Americana? Americanos ? lose again :D nothing new. U gota rekt on MLG.\n" +
                "2016-03-29 10:45:45\n" +
                "i_remember_a_guardian_flick - <3 flusha\n" +
                "#60\n" +
                "Alliance is back EleGiggle 4head PogChamp\n" +
                "2016-03-29 11:07:40\n" +
                "Kuratani\n" +
                "#62\n" +
                "TS <3\n" +
                "2016-03-29 11:30:31\n" +
                "CykaKing\n" +
                "#65\n" +
                "ayy\n" +
                "2016-03-29 12:17:35\n" +
                "runnr\n" +
                "#66\n" +
                "Good job :)\n" +
                "2016-03-29 12:59:04\n" +
                "rgmarks\n" +
                "#67\n" +
                "Even tempo storm won something, and LG still dont.\n" +
                "2016-03-29 13:31:26\n" +
                "WetNippleClamps\n" +
                "#69\n" +
                "Gg\n" +
                "2016-03-29 13:37:00\n" +
                "You are replying to the main post\n" +
                "\n" +
                "\n" +
                "This is an international site, please write your comment in English. Comments in another language will be deleted.\n" +
                " \n";
        HashMap<String, Integer> frequenties = HuffmanCodering.getFrequentie(message);
        PriorityQueue<HuffKnoop> priorityFrequentie = HuffmanCodering.sortFrequentie(frequenties);
        HuffKnoop h = HuffmanCodering.createHuffmanTree(priorityFrequentie);
        HashMap<Character, String> characterCodes = HuffmanCodering.createCodes(h, "");
        String result = HuffmanCodering.decode(HuffmanCodering.encode(characterCodes, message), priorityFrequentie.peek());
        assertEquals(result, message);
    }
}
