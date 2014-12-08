/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ressources.definitions;

import geometry.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import math.MyRandom;
import model.army.ArmyManager;
import model.army.data.ActorBuilder;
import model.army.data.EffectBuilder;
import model.army.data.MoverBuilder;
import model.army.data.ProjectileBuilder;
import model.army.data.TurretBuilder;
import model.army.data.UnitBuilder;
import model.army.data.WeaponBuilder;
import ressources.definitions.Definition;
import model.map.Map;
import model.map.data.CliffShapeBuilder;
import model.map.data.ManmadeFaceBuilder;
import model.map.data.NaturalFaceBuilder;
import model.map.data.TrinketBuilder;
import model.warfare.Faction;
import tools.LogUtil;

/**
 *
 * @author Benoît
 */
public class BuilderLibrary {
    private static final String ERROR = "Impossible to find ";
    
    private static final String UNIT = "Unit";
    private static final String MOVER = "Mover";
    private static final String WEAPON = "Weapon";
    private static final String TURRET = "Turret";
    private static final String EFFECT = "Effect";
    private static final String PROJECTILE = "Projectile";
    private static final String ACTOR = "Actor";
    
    private static final String CLIFF_SHAPE = "CliffShape";
    private static final String TRINKET = "Trinket";
    private static final String NATURAL_FACE = "NaturalFace";
    private static final String MANMADE_FACE = "ManmadeFace";


    
    private HashMap<String, UnitBuilder> unitBuilders = new HashMap<>();
    private HashMap<String, MoverBuilder> moverBuilders = new HashMap<>();
    private HashMap<String, WeaponBuilder> weaponBuilders = new HashMap<>();
    private HashMap<String, TurretBuilder> turretBuilders = new HashMap<>();
    private HashMap<String, EffectBuilder> effectBuilders = new HashMap<>();
    private HashMap<String, ProjectileBuilder> projectileBuilders = new HashMap<>();
    private HashMap<String, ActorBuilder> actorBuilders = new HashMap<>();

    private HashMap<String, CliffShapeBuilder> cliffShapeBuilders = new HashMap<>();
    private HashMap<String, TrinketBuilder> trinketBuilders = new HashMap<>();
    private HashMap<String, NaturalFaceBuilder> naturalFaceBuilders = new HashMap<>();
    private HashMap<String, ManmadeFaceBuilder> manmadeFaceBuilders = new HashMap<>();

    Map map;
    ArmyManager am;
    
    public BuilderLibrary(Map map, ArmyManager am){
        this.map = map;
        this.am = am;
    }
    
    
    public void submit(Definition def){
        switch (def.type){
            case UNIT : submitUnit(def); break;
            case MOVER : submitMover(def); break;
            case WEAPON : submitWeapon(def); break;
            case TURRET : submitTurret(def); break;
            case EFFECT : submitEffect(def); break;
            case PROJECTILE : submitProjectile(def); break;
            case ACTOR : submitActor(def); break;

            case CLIFF_SHAPE : submitCliffShape(def); break;
            case TRINKET : submitTrinket(def); break;
            case NATURAL_FACE : submitNaturalFace(def); break;
            case MANMADE_FACE : submitManmadeFace(def); break;
        }
    }
    
    private void submitUnit(Definition def){
        unitBuilders.put(def.id, new UnitBuilder(def, am, this));
    }

    private void submitMover(Definition def){
        moverBuilders.put(def.id, new MoverBuilder(def, map));
    }

    private void submitWeapon(Definition def){
        weaponBuilders.put(def.id, new WeaponBuilder(def, this));
    }

    private void submitTurret(Definition def){
        turretBuilders.put(def.id, new TurretBuilder(def));
    }

    private void submitEffect(Definition def){
        effectBuilders.put(def.id, new EffectBuilder(def, am, this));
    }

    private void submitProjectile(Definition def){
        projectileBuilders.put(def.id, new ProjectileBuilder(def, this, am));
    }
    private void submitActor(Definition def){
        actorBuilders.put(def.id, new ActorBuilder(def, am, this));
    }
    
    private void submitCliffShape(Definition def){
        cliffShapeBuilders.put(def.id, new CliffShapeBuilder(def, this));
    }
    private void submitTrinket(Definition def){
        trinketBuilders.put(def.id, new TrinketBuilder(def));
    }
    private void submitNaturalFace(Definition def){
        naturalFaceBuilders.put(def.id, new NaturalFaceBuilder(def));
    }
    private void submitManmadeFace(Definition def){
        manmadeFaceBuilders.put(def.id, new ManmadeFaceBuilder(def));
    }
    
    
    
    
    public void buildUnitFromRace(String race, Faction faction, Point2D pos){
        ArrayList<UnitBuilder> subList = new ArrayList<>();
        for(UnitBuilder ub : unitBuilders.values())
            if(ub.race.equals(race))
                subList.add(ub);
        
        int i = (int)Math.floor(MyRandom.next()*subList.size());
        subList.get(i).build(faction, pos);
    }
    
    public UnitBuilder getUnitBuilder(String id){
        UnitBuilder res = unitBuilders.get(id);
        if(res == null)
            throw new IllegalArgumentException(ERROR+id);
        return res;
    }
    public MoverBuilder getMoverBuilder(String id){
        MoverBuilder res = moverBuilders.get(id);
        if(res == null)
            throw new IllegalArgumentException(ERROR+id);
        return res;
    }
    public WeaponBuilder getWeaponBuilder(String id){
        WeaponBuilder res = weaponBuilders.get(id);
        if(res == null)
            throw new IllegalArgumentException(ERROR+id);
        return res;
    }
    public TurretBuilder getTurretBuilder(String id){
        TurretBuilder res = turretBuilders.get(id);
        if(res == null)
            throw new IllegalArgumentException(ERROR+id);
        return res;
    }
    public EffectBuilder getEffectBuilder(String id){
        EffectBuilder res = effectBuilders.get(id);
        if(res == null)
            throw new IllegalArgumentException(ERROR+id);
        return res;
    }
    public ProjectileBuilder getProjectileBuilder(String id){
        ProjectileBuilder res = projectileBuilders.get(id);
        if(res == null)
            throw new IllegalArgumentException(ERROR+id);
        return res;
    }
    public ActorBuilder getActorBuilder(String id){
        ActorBuilder res = actorBuilders.get(id);
        if(res == null)
            throw new IllegalArgumentException(ERROR+id);
        return res;
    }
    
    
    
    public CliffShapeBuilder getCliffShapeBuilder(String id){
        CliffShapeBuilder res = cliffShapeBuilders.get(id);
        if(res == null)
            throw new IllegalArgumentException(ERROR+id);
        return res;
    }
    public TrinketBuilder getTrinketBuilder(String id){
        TrinketBuilder res = trinketBuilders.get(id);
        if(res == null)
            throw new IllegalArgumentException(ERROR+id);
        return res;
    }
    public NaturalFaceBuilder getNaturalFaceBuilder(String id){
        NaturalFaceBuilder res = naturalFaceBuilders.get(id);
        if(res == null)
            throw new IllegalArgumentException(ERROR+id);
        return res;
    }
    public ManmadeFaceBuilder getManmadeFaceBuilder(String id){
        ManmadeFaceBuilder res = manmadeFaceBuilders.get(id);
        if(res == null)
            throw new IllegalArgumentException(ERROR+id);
        return res;
    }
    
    
}