package game.systems;

import game.components.DimensionComponent;
import game.components.PositionComponent;
import game.components.SpriteComponent;

import java.util.HashMap;

import lombok.Getter;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class RenderSystem
		extends EntityProcessingSystem {

	@Mapper
	private ComponentMapper<PositionComponent> positionMapper;
	@Mapper
	private ComponentMapper<SpriteComponent> spriteMapper;
	@Mapper
	private ComponentMapper<DimensionComponent> dimensionMapper;

	private HashMap<String, AtlasRegion> regions;
	// private TextureAtlas textureAtlas;
	@Getter
	private SpriteBatch batch;
	private OrthographicCamera camera;

	private Entity player;
	private TextureAtlas textureAtlas;

	@SuppressWarnings("unchecked")
	public RenderSystem(OrthographicCamera camera) {
		super(Aspect.getAspectForAll(SpriteComponent.class, PositionComponent.class, DimensionComponent.class));
		this.camera = camera;
		this.player = player;

		OrthographicCamera guicam = new OrthographicCamera();
		float ratew = guicam.viewportWidth / camera.viewportWidth;  // <--- you should calculate these 2 only once.
		float rateh = guicam.viewportHeight / camera.viewportHeight;

	}

	@Override
	public void initialize() {
		long millis = TimeUtils.millis();
		regions = new HashMap<String, AtlasRegion>();
		// textureAtlas = new TextureAtlas(Gdx.files.internal("assets/packed-textures/textures.pack"),
		// Gdx.files.internal("assets/packed-textures"));
		textureAtlas = new TextureAtlas(Gdx.files.internal("assets/packed-textures/textures.pack"));
		float elapsed = (TimeUtils.millis() - millis) / 1000f;
		System.out.println("Loading textures " + elapsed);
		// for (AtlasRegion r : textureAtlas.getRegions()) {
		// regions.put(r.name, r);
		// }
		batch = new SpriteBatch();

	}

	@Override
	protected void begin() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

	@Override
	protected void inserted(Entity entity) {
		SpriteComponent spriteComp = spriteMapper.get(entity);
		// AtlasRegion spriteRegion = regions.get(spriteComp.getName());
		// System.out.println(spriteComp.getName());
		AtlasRegion spriteRegion = textureAtlas.findRegion(spriteComp.getName());
		Sprite sprite = new Sprite(spriteRegion);
		Vector2 size = new Vector2(dimensionMapper.get(entity).getLength(), dimensionMapper.get(entity).getWidth());
		Vector2 origin = new Vector2(sprite.getWidth() / 2, sprite.getHeight() / 2);
		// screenParams.convert(origin);
		// screenParams.convert(size);
		sprite.setSize(size.x, size.y);
		sprite.setOrigin(origin.x, origin.y);
		spriteComp.setSprite(sprite);
		spriteComp.setSet(true);

	}

	@Override
	protected void process(Entity entity) {
		Vector2 position = getPosition(entity);
		SpriteComponent spriteComp = spriteMapper.get(entity);
		DimensionComponent dimComp = dimensionMapper.get(entity);

		if (position != null) {
			if (!spriteComp.isSet()) {
				inserted(entity);
			}
			Sprite sprite = spriteComp.getSprite();

			float posX = position.x - sprite.getWidth() / 2;
			float posY = position.y - sprite.getHeight() / 2;
			TextureRegion textureRegion = spriteComp.getRegion();
			if (textureRegion != null) {
				batch.draw(textureRegion, posX, posY);
			}

			else {
				sprite.setPosition(posX, posY);
				sprite.draw(batch);
			}
		}
	}

	private Vector2 getPosition(Entity e) {
		return positionMapper.get(e).getPosition();
	}

	@Override
	protected void end() {
		batch.end();
	}

}
