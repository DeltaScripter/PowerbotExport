package web.data;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

import web.components.lines.WebComponent;
import web.components.lines.WebLine;


/**
 * Author: Tom
 * Date: 07/04/12
 * Time: 23:31
 */
public class FTPLines extends ClientAccessor{
	
	public FTPLines(ClientContext ctx){
		super(ctx);
	}
	
public WebComponent[] componentArray = {
	
	new WebLine(ctx,new Tile(3212, 3431, 0),new Tile(3212, 3431, 0)),
	
	
	//this part goes west from varrock fountain
	new WebLine(ctx, new Tile(3212, 3431, 0),new Tile(3206, 3427, 0)),
	new WebLine(ctx, new Tile(3206, 3427, 0),new Tile(3198, 3428, 0)),
	new WebLine(ctx, new Tile(3198, 3428, 0),new Tile(3193, 3429, 0)),
	new WebLine(ctx, new Tile(3193, 3429, 0),new Tile(3187, 3428, 0)),
	new WebLine(ctx, new Tile(3187, 3428, 0),new Tile(3181, 3429, 0)),
	new WebLine(ctx, new Tile(3181, 3429, 0),new Tile(3176, 3427, 0)),
	new WebLine(ctx, new Tile(3176, 3427, 0),new Tile(3170, 3427, 0)),
	new WebLine(ctx, new Tile(3170, 3427, 0),new Tile(3165, 3422, 0)),
	new WebLine(ctx, new Tile(3165, 3422, 0),new Tile(3152, 3417, 0)),
	new WebLine(ctx, new Tile(3152, 3417, 0),new Tile(3145, 3416, 0)),
	new WebLine(ctx, new Tile(3145, 3416, 0),new Tile(3137, 3417, 0)),
	new WebLine(ctx, new Tile(3137, 3417, 0),new Tile(3130, 3414, 0)),
	new WebLine(ctx, new Tile(3130, 3414, 0),new Tile(3122, 3416, 0)),
	new WebLine(ctx, new Tile(3122, 3416, 0),new Tile(3116, 3419, 0)),
	new WebLine(ctx, new Tile(3116, 3419, 0),new Tile(3110, 3420, 0)),
	new WebLine(ctx, new Tile(3110, 3420, 0),new Tile(3098, 3419, 0)),
	new WebLine(ctx, new Tile(3098, 3419, 0),new Tile(3092, 3419, 0)),
	new WebLine(ctx, new Tile(3092, 3419, 0),new Tile(3084, 3418, 0)),
//^^ ends in the center of barbarian village
	
	
	//this part goes east from varrock fountain
	new WebLine(ctx, new Tile(3212, 3431, 0),new Tile(3220, 3428, 0)),
	new WebLine(ctx, new Tile(3220, 3428, 0),new Tile(3230, 3428, 0)),
	new WebLine(ctx, new Tile(3230, 3428, 0),new Tile(3239, 3429, 0)),
	new WebLine(ctx, new Tile(3239, 3429, 0),new Tile(3248, 3428, 0)),
	new WebLine(ctx, new Tile(3248, 3428, 0),new Tile(3256, 3428, 0)),
	new WebLine(ctx, new Tile(3256, 3428, 0),new Tile(3263, 3426, 0)),
	new WebLine(ctx, new Tile(3263, 3426, 0),new Tile(3264, 3418, 0)),
	new WebLine(ctx, new Tile(3264, 3418, 0),new Tile(3260, 3412, 0)),
	new WebLine(ctx, new Tile(3260, 3412, 0),new Tile(3259, 3404, 0)),
	new WebLine(ctx, new Tile(3259, 3404, 0),new Tile(3257, 3399, 0)),
	new WebLine(ctx, new Tile(3257, 3399, 0),new Tile(3253, 3397, 0)),
	new WebLine(ctx, new Tile(3253, 3397, 0),new Tile(3249, 3399, 0)),
	new WebLine(ctx, new Tile(3249, 3399, 0),new Tile(3244, 3401, 0)),
	new WebLine(ctx, new Tile(3244, 3401, 0),new Tile(3243, 3406, 0)),
	new WebLine(ctx, new Tile(3243, 3406, 0),new Tile(3244, 3412, 0)),
	new WebLine(ctx, new Tile(3244, 3412, 0),new Tile(3242, 3418, 0)),
	new WebLine(ctx, new Tile(3242, 3418, 0),new Tile(3239, 3429, 0)),

	//goes south down to lumbridge
	new WebLine(ctx, new Tile(3212, 3431, 0),new Tile(3211, 3421, 0)),
	new WebLine(ctx, new Tile(3211, 3421, 0),new Tile(3211, 3412, 0)),
	new WebLine(ctx, new Tile(3211, 3412, 0),new Tile(3212, 3403, 0)),
	new WebLine(ctx, new Tile(3212, 3403, 0),new Tile(3211, 3393, 0)),
	new WebLine(ctx, new Tile(3211, 3393, 0),new Tile(3211, 3384, 0)),
	new WebLine(ctx, new Tile(3211, 3384, 0),new Tile(3212, 3376, 0)),
	new WebLine(ctx, new Tile(3212, 3376, 0),new Tile(3213, 3368, 0)),
	new WebLine(ctx, new Tile(3213, 3368, 0),new Tile(3218, 3361, 0)),
	new WebLine(ctx, new Tile(3218, 3361, 0),new Tile(3224, 3355, 0)),
	new WebLine(ctx, new Tile(3224, 3355, 0),new Tile(3226, 3350, 0)),
	new WebLine(ctx, new Tile(3226, 3350, 0),new Tile(3226, 3342, 0)),
	new WebLine(ctx, new Tile(3226, 3342, 0),new Tile(3230, 3335, 0)),
	new WebLine(ctx, new Tile(3230, 3335, 0),new Tile(3239, 3335, 0)),
	new WebLine(ctx, new Tile(3239, 3335, 0),new Tile(3244, 3337, 0)),
	new WebLine(ctx, new Tile(3244, 3337, 0),new Tile(3253, 3334, 0)),
	new WebLine(ctx, new Tile(3253, 3334, 0),new Tile(3262, 3333, 0)),
	new WebLine(ctx, new Tile(3262, 3333, 0),new Tile(3268, 3329, 0)),
	new WebLine(ctx, new Tile(3268, 3329, 0),new Tile(3264, 3324, 0)),
	new WebLine(ctx, new Tile(3264, 3324, 0),new Tile(3256, 3323, 0)),
	new WebLine(ctx, new Tile(3256, 3323, 0),new Tile(3251, 3318, 0)),
	new WebLine(ctx, new Tile(3251, 3318, 0),new Tile(3247, 3313, 0)),
	new WebLine(ctx, new Tile(3247, 3313, 0),new Tile(3241, 3307, 0)),
	new WebLine(ctx, new Tile(3241, 3307, 0),new Tile(3239, 3297, 0)),
	new WebLine(ctx, new Tile(3239, 3297, 0),new Tile(3238, 3289, 0)),
	new WebLine(ctx, new Tile(3238, 3289, 0),new Tile(3241, 3281, 0)),
	new WebLine(ctx, new Tile(3241, 3281, 0),new Tile(3249, 3267, 0)),
	new WebLine(ctx, new Tile(3249, 3267, 0),new Tile(3249, 3259, 0)),
	new WebLine(ctx, new Tile(3249, 3259, 0),new Tile(3251, 3251, 0)),
	new WebLine(ctx, new Tile(3251, 3251, 0),new Tile(3256, 3244, 0)),
	new WebLine(ctx, new Tile(3256, 3244, 0),new Tile(3257, 3236, 0)),
	new WebLine(ctx, new Tile(3257, 3236, 0),new Tile(3257, 3228, 0)),
	new WebLine(ctx, new Tile(3257, 3228, 0),new Tile(3251, 3225, 0)),
	new WebLine(ctx, new Tile(3251, 3225, 0),new Tile(3247, 3225, 0)),
	new WebLine(ctx, new Tile(3247, 3225, 0),new Tile(3241,3225, 0)),
	
	new WebLine(ctx, new Tile(3241, 3225, 0),new Tile(3235, 3225, 0)),
	new WebLine(ctx, new Tile(3235, 3225, 0),new Tile(3232, 3229, 0)),
	new WebLine(ctx, new Tile(3232, 3229, 0),new Tile(3228, 3234, 0)),
	new WebLine(ctx, new Tile(3228, 3234, 0),new Tile(3223, 3236, 0)),
	new WebLine(ctx, new Tile(3223, 3236, 0),new Tile(3220, 3240, 0)),
	new WebLine(ctx, new Tile(3220, 3240, 0),new Tile(3219, 3246, 0)),
	new WebLine(ctx, new Tile(3219, 3246, 0),new Tile(3217, 3250, 0)),
	new WebLine(ctx, new Tile(3217, 3250, 0),new Tile(3213, 3253, 0)),
	new WebLine(ctx, new Tile(3213, 3253, 0),new Tile(3206, 3252, 0)),
	new WebLine(ctx, new Tile(3206, 3252, 0),new Tile(3199, 3251, 0)),
	new WebLine(ctx, new Tile(3199, 3251, 0),new Tile(3193, 3248, 0)),
	new WebLine(ctx, new Tile(3193, 3248, 0),new Tile(3188, 3244, 0)),
	new WebLine(ctx, new Tile(3188, 3244, 0),new Tile(3181, 3243, 0)),
	new WebLine(ctx, new Tile(3181, 3243, 0),new Tile(3166, 3237, 0)),
	new WebLine(ctx, new Tile(3166, 3237, 0),new Tile(3158, 3235, 0)),
	new WebLine(ctx, new Tile(3158, 3235, 0),new Tile(3149, 3234, 0)),
	new WebLine(ctx, new Tile(3149, 3234, 0),new Tile(3142, 3229, 0)),
	new WebLine(ctx, new Tile(3142, 3229, 0),new Tile(3134, 3226, 0)),
	new WebLine(ctx, new Tile(3134, 3226, 0),new Tile(3126, 3223, 0)),
	new WebLine(ctx, new Tile(3126, 3223, 0),new Tile(3120, 3216, 0)),
	new WebLine(ctx, new Tile(3120, 3216, 0),new Tile(3116, 3210, 0)),
	new WebLine(ctx, new Tile(3116, 3210, 0),new Tile(3114, 3204, 0)),
	new WebLine(ctx, new Tile(3114, 3204, 0),new Tile(3114, 3195, 0)),
	new WebLine(ctx, new Tile(3114, 3195, 0),new Tile(3114, 3186, 0)),
	new WebLine(ctx, new Tile(3114, 3186, 0),new Tile(3114, 3178, 0)),
	new WebLine(ctx, new Tile(3114, 3178, 0),new Tile(3114, 3169, 0)),
	new WebLine(ctx, new Tile(3114, 3169, 0),new Tile(3109, 3168, 0)),
	
	//leads t o lumbridge swamp
	new WebLine(ctx, new Tile(3235, 3225, 0),new Tile(3235, 3217, 0)),
	new WebLine(ctx, new Tile(3235, 3217, 0),new Tile(3236, 3210, 0)),
	new WebLine(ctx, new Tile(3236, 3210, 0),new Tile(3235, 3205, 0)),
	new WebLine(ctx, new Tile(3235, 3205, 0),new Tile(3240, 3200, 0)),
	new WebLine(ctx, new Tile(3240, 3200, 0),new Tile(3242, 3194, 0)),
	new WebLine(ctx, new Tile(3242, 3194, 0),new Tile(3244, 3191, 0)),
	new WebLine(ctx, new Tile(3244, 3191, 0),new Tile(3243, 3184, 0)),
	new WebLine(ctx, new Tile(3243, 3184, 0),new Tile(3240, 3177, 0)),
	new WebLine(ctx, new Tile(3240, 3177, 0),new Tile(3238, 3167, 0)),
	new WebLine(ctx, new Tile(3238, 3167, 0),new Tile(3236, 3158, 0)),
	new WebLine(ctx, new Tile(3236, 3158, 0),new Tile(3231, 3153, 0)),
	new WebLine(ctx, new Tile(3231, 3153, 0),new Tile(3215, 3151, 0)),
	new WebLine(ctx, new Tile(3215, 3151, 0),new Tile(3206, 3154, 0)),
	new WebLine(ctx, new Tile(3206, 3154, 0),new Tile(3197, 3156, 0)),
	new WebLine(ctx, new Tile(3197, 3156, 0),new Tile(3187, 3153, 0)),
	new WebLine(ctx, new Tile(3187, 3153, 0),new Tile(3178, 3154, 0)),
	new WebLine(ctx, new Tile(3178, 3154, 0),new Tile(3168, 3155, 0)),
	new WebLine(ctx, new Tile(3168, 3155, 0),new Tile(3156, 3159, 0)),
	new WebLine(ctx, new Tile(3156, 3159, 0),new Tile(3152, 3163, 0)),
	new WebLine(ctx, new Tile(3152, 3163, 0),new Tile(3149, 3165, 0)),
	new WebLine(ctx, new Tile(3149, 3165, 0),new Tile(3147, 3169, 0)),
	new WebLine(ctx, new Tile(3147, 3169, 0),new Tile(3147, 3171, 0)),
	
	new WebLine(ctx, new Tile(3213, 3253, 0),new Tile(3216, 3257, 0)),
	new WebLine(ctx, new Tile(3216, 3257, 0),new Tile(3217, 3263, 0)),
	new WebLine(ctx, new Tile(3217, 3263, 0),new Tile(3216, 3270, 0)),
	new WebLine(ctx, new Tile(3216, 3270, 0),new Tile(3217, 3274, 0)),
	new WebLine(ctx, new Tile(3217, 3274, 0),new Tile(3209, 3277, 0)),
	new WebLine(ctx, new Tile(3209, 3277, 0),new Tile(3204, 3278, 0)),
	new WebLine(ctx, new Tile(3204, 3278, 0),new Tile(3200, 3279, 0)),
	new WebLine(ctx, new Tile(3200, 3279, 0),new Tile(3191, 3283, 0)),
	new WebLine(ctx, new Tile(3191, 3283, 0),new Tile(3187, 3282, 0)),
	new WebLine(ctx, new Tile(3187, 3282, 0),new Tile(3183, 3287, 0)),
	new WebLine(ctx, new Tile(3183, 3287, 0),new Tile(3171, 3285, 0)),
	new WebLine(ctx, new Tile(3171, 3285, 0),new Tile(3167, 3289, 0)),
	new WebLine(ctx, new Tile(3167, 3289, 0),new Tile(3162, 3288, 0)),
	new WebLine(ctx, new Tile(3162, 3288, 0),new Tile(3157, 3291, 0)),
	new WebLine(ctx, new Tile(3157, 3291, 0),new Tile(3153, 3292, 0)),
	new WebLine(ctx, new Tile(3153, 3292, 0),new Tile(3148, 3295, 0)),
	new WebLine(ctx, new Tile(3148, 3295, 0),new Tile(3143, 3293, 0)),
	new WebLine(ctx, new Tile(3143, 3293, 0),new Tile(3137, 3295, 0)),
	new WebLine(ctx, new Tile(3137, 3295, 0),new Tile(3132, 3294, 0)),
	new WebLine(ctx, new Tile(3132, 3294, 0),new Tile(3126, 3298, 0)),
	new WebLine(ctx, new Tile(3126, 3298, 0),new Tile(3121, 3298, 0)),
	new WebLine(ctx, new Tile(3121, 3298, 0),new Tile(3116, 3295, 0)),
	new WebLine(ctx, new Tile(3116, 3295, 0),new Tile(3110, 3294, 0)),
	new WebLine(ctx, new Tile(3110, 3294, 0),new Tile(3104, 3294, 0)),
	new WebLine(ctx, new Tile(3104, 3294, 0),new Tile(3094, 3291, 0)),
	new WebLine(ctx, new Tile(3094, 3291, 0),new Tile(3087, 3288, 0)),
	new WebLine(ctx, new Tile(3087, 3288, 0),new Tile(3080, 3287, 0)),
	new WebLine(ctx, new Tile(3080, 3287, 0),new Tile(3077, 3282, 0)),
	new WebLine(ctx, new Tile(3077, 3282, 0),new Tile(3074, 3277, 0)),
	new WebLine(ctx, new Tile(3074, 3277, 0),new Tile(3070, 3276, 0)),
	
	
	new WebLine(ctx, new Tile(3167, 3289, 0),new Tile(3166, 3293, 0)),
	new WebLine(ctx, new Tile(3166, 3293, 0),new Tile(3166, 3298, 0)),
	new WebLine(ctx, new Tile(3166, 3298, 0),new Tile(3166, 3302, 0)),
	
	new WebLine(ctx, new Tile(3235, 3217, 0),new Tile(3230, 3218, 0)),
	new WebLine(ctx, new Tile(3230, 3218, 0),new Tile(3225, 3218, 0)),
	new WebLine(ctx, new Tile(3225, 3218, 0),new Tile(3220, 3218, 0)),
	new WebLine(ctx, new Tile(3220, 3218, 0),new Tile(3215, 3219, 0)),
	new WebLine(ctx, new Tile(3215, 3219, 0),new Tile(3206, 3227, 0)),

	/*
	new WebLine(ctx, new Tile(3116, 3218, 0), new Tile(3139, 3209, 0))
	, new WebLine(ctx, new Tile(3116, 3218, 0), new Tile(3190, 3244, 0))
	, new WebLine(ctx, new Tile(3190, 3244, 0), new Tile(3224, 3245, 0))
	, new WebLine(ctx, new Tile(3224, 3245, 0), new Tile(3234, 3219, 0))
	, new WebLine(ctx, new Tile(3234, 3219, 0), new Tile(3241, 3182, 0))
	, new WebLine(ctx, new Tile(3241, 3182, 0), new Tile(3208, 3162, 0))
	, new WebLine(ctx, new Tile(3208, 3162, 0), new Tile(3229, 3149, 0))
	, new WebLine(ctx, new Tile(3208, 3162, 0), new Tile(3173, 3165, 0))
	, new WebLine(ctx, new Tile(3173, 3165, 0), new Tile(3148, 3147, 0))
	, new WebLine(ctx, new Tile(3173, 3165, 0), new Tile(3139, 3209, 0))
	, new WebLine(ctx, new Tile(3116, 3218, 0), new Tile(3103, 3250, 0))
	, new WebLine(ctx, new Tile(3103, 3250, 0), new Tile(3107, 3295, 0))
	, new WebLine(ctx, new Tile(3107, 3295, 0), new Tile(3109, 3326, 0))
	, new WebLine(ctx, new Tile(3109, 3326, 0), new Tile(3080, 3326, 0))
	, new WebLine(ctx, new Tile(3107, 3295, 0), new Tile(3080, 3326, 0))
	, new WebLine(ctx, new Tile(3076, 3385, 0), new Tile(3098, 3421, 0))
	, new WebLine(ctx, new Tile(3224, 3245, 0), new Tile(3212, 3280, 0))
	, new WebLine(ctx, new Tile(3212, 3280, 0), new Tile(3167, 3286, 0))
	, new WebLine(ctx, new Tile(3167, 3286, 0), new Tile(3107, 3295, 0))
	, new WebLine(ctx, new Tile(3151, 3417, 0), new Tile(3187, 3429, 0))
	, new WebLine(ctx, new Tile(3187, 3429, 0), new Tile(3186, 3439, 0))
	, new WebLine(ctx, new Tile(3187, 3429, 0), new Tile(3211, 3428, 0))
	, new WebLine(ctx, new Tile(3211, 3428, 0), new Tile(3210, 3373, 0))
	, new WebLine(ctx, new Tile(3211, 3428, 0), new Tile(3253, 3429, 0))
	, new WebLine(ctx, new Tile(3210, 3373, 0), new Tile(3182, 3369, 0))
	, new WebLine(ctx, new Tile(3210, 3373, 0), new Tile(3230, 3336, 0))
	, new WebLine(ctx, new Tile(3230, 3336, 0), new Tile(3267, 3334, 0))
	, new WebLine(ctx, new Tile(3267, 3334, 0), new Tile(3240, 3307, 0))
	, new WebLine(ctx, new Tile(3230, 3336, 0), new Tile(3240, 3307, 0))
	, new WebLine(ctx, new Tile(3267, 3334, 0), new Tile(3282, 3331, 0))
	, new WebLine(ctx, new Tile(3269, 3166, 0), new Tile(3277, 3227, 0))
	, new WebLine(ctx, new Tile(3277, 3227, 0), new Tile(3287, 3275, 0))
	, new WebLine(ctx, new Tile(3287, 3275, 0), new Tile(3282, 3331, 0))
	, new WebLine(ctx, new Tile(3287, 3275, 0), new Tile(3300, 3309, 0))
	, new WebLine(ctx, new Tile(3269, 3166, 0), new Tile(3241, 3182, 0))
	, new WebLine(ctx, new Tile(3116, 3218, 0), new Tile(3112, 3169, 0))
	, new WebLine(ctx, new Tile(3107, 3295, 0), new Tile(3074, 3274, 0))
	, new WebLine(ctx, new Tile(3074, 3274, 0), new Tile(3080, 3326, 0))
	, new WebLine(ctx, new Tile(3033, 3276, 0), new Tile(3046, 3247, 0))
	, new WebLine(ctx, new Tile(3033, 3276, 0), new Tile(3005, 3278, 0))
	, new WebLine(ctx, new Tile(3005, 3278, 0), new Tile(3019, 3243, 0))
	, new WebLine(ctx, new Tile(3019, 3243, 0), new Tile(3033, 3276, 0))
	, new WebLine(ctx, new Tile(3019, 3243, 0), new Tile(3046, 3247, 0))
	, new WebLine(ctx, new Tile(3019, 3205, 0), new Tile(3019, 3243, 0))
	, new WebLine(ctx, new Tile(3019, 3205, 0), new Tile(3038, 3204, 0))
	, new WebLine(ctx, new Tile(3019, 3205, 0), new Tile(3009, 3156, 0))
	, new WebLine(ctx, new Tile(3005, 3278, 0), new Tile(3007, 3320, 0))
	, new WebLine(ctx, new Tile(3007, 3320, 0), new Tile(3055, 3322, 0))
	, new WebLine(ctx, new Tile(3005, 3278, 0), new Tile(2978, 3239, 0))
	, new WebLine(ctx, new Tile(3019, 3243, 0), new Tile(2978, 3239, 0))
	, new WebLine(ctx, new Tile(3019, 3205, 0), new Tile(2978, 3239, 0))
	, new WebLine(ctx, new Tile(3007, 3320, 0), new Tile(3005, 3362, 0))
	, new WebLine(ctx, new Tile(3005, 3362, 0), new Tile(2965, 3380, 0))
	, new WebLine(ctx, new Tile(2965, 3380, 0), new Tile(2968, 3416, 0))
	, new WebLine(ctx, new Tile(2968, 3416, 0), new Tile(3000, 3433, 0))
	, new WebLine(ctx, new Tile(3000, 3433, 0), new Tile(3079, 3420, 0))
	, new WebLine(ctx, new Tile(3079, 3420, 0), new Tile(3098, 3421, 0))
	, new WebLine(ctx, new Tile(3079, 3420, 0), new Tile(3076, 3385, 0))
	, new WebLine(ctx, new Tile(3253, 3429, 0), new Tile(3284, 3427, 0))
	, new WebLine(ctx, new Tile(3284, 3427, 0), new Tile(3285, 3365, 0))
	, new WebLine(ctx, new Tile(3285, 3365, 0), new Tile(3282, 3331, 0))
	, new WebLine(ctx, new Tile(3285, 3365, 0), new Tile(3267, 3334, 0))
	, new WebLine(ctx, new Tile(3151, 3417, 0), new Tile(3182, 3369, 0))
	, new WebLine(ctx, new Tile(3253, 3429, 0), new Tile(3245, 3462, 0))
	, new WebLine(ctx, new Tile(3245, 3462, 0), new Tile(3230, 3465, 0))
	, new WebLine(ctx, new Tile(3230, 3465, 0), new Tile(3212, 3466, 0))
	, new WebLine(ctx, new Tile(3212, 3466, 0), new Tile(3212, 3449, 0))
	, new WebLine(ctx, new Tile(3212, 3449, 0), new Tile(3197, 3458, 0))
	, new WebLine(ctx, new Tile(3197, 3458, 0), new Tile(3196, 3492, 0))
	, new WebLine(ctx, new Tile(3196, 3492, 0), new Tile(3164, 3488, 0))
	, new WebLine(ctx, new Tile(3151, 3417, 0), new Tile(3165, 3456, 0))
	, new WebLine(ctx, new Tile(3165, 3456, 0), new Tile(3186, 3439, 0))
	, new WebLine(ctx, new Tile(3165, 3456, 0), new Tile(3164, 3488, 0))
	, new WebLine(ctx, new Tile(3167, 3286, 0), new Tile(3182, 3369, 0))
	, new WebLine(ctx, new Tile(3253, 3418, 0), new Tile(3253, 3429, 0))
	, new WebLine(ctx, new Tile(3000, 3433, 0), new Tile(3051, 3457, 0))
	, new WebLine(ctx, new Tile(3079, 3420, 0), new Tile(3051, 3457, 0))
	, new WebLine(ctx, new Tile(3098, 3421, 0), new Tile(3095, 3450, 0))
	, new WebLine(ctx, new Tile(3095, 3450, 0), new Tile(3095, 3493, 0))
	, new WebLine(ctx, new Tile(3095, 3493, 0), new Tile(3050, 3516, 0))
	, new WebLine(ctx, new Tile(3050, 3516, 0), new Tile(3052, 3490, 0))
	, new WebLine(ctx, new Tile(3052, 3490, 0), new Tile(3051, 3457, 0))
	, new WebLine(ctx, new Tile(2946, 3368, 0), new Tile(2965, 3380, 0))
	, new WebLine(ctx, new Tile(3005, 3362, 0), new Tile(3014, 3354, 0))
	, new WebLine(ctx, new Tile(3005, 3362, 0), new Tile(3039, 3358, 0))
	, new WebLine(ctx, new Tile(3014, 3354, 0), new Tile(3039, 3358, 0))
	, new WebLine(ctx, new Tile(3039, 3358, 0), new Tile(3060, 3374, 0))
	, new WebLine(ctx, new Tile(3039, 3358, 0), new Tile(3048, 3338, 0))
	, new WebLine(ctx, new Tile(2965, 3380, 0), new Tile(2969, 3339, 0))
	, new WebLine(ctx, new Tile(3224, 3245, 0), new Tile(3258, 3231, 0))
	, new WebLine(ctx, new Tile(3258, 3231, 0), new Tile(3242, 3272, 0))
	, new WebLine(ctx, new Tile(3242, 3272, 0), new Tile(3240, 3307, 0))
	, new WebLine(ctx, new Tile(3151, 3417, 0), new Tile(3114, 3420, 0))
	, new WebLine(ctx, new Tile(3212, 3280, 0), new Tile(3228, 3261, 0))
	, new WebLine(ctx, new Tile(3224, 3245, 0), new Tile(3228, 3261, 0))
	, new WebLine(ctx, new Tile(3228, 3261, 0), new Tile(3241, 3261, 0))
	, new WebLine(ctx, new Tile(3241, 3261, 0), new Tile(3242, 3272, 0))
	, new WebLine(ctx, new Tile(3241, 3261, 0), new Tile(3258, 3231, 0))
	, new WebLine(ctx, new Tile(3109, 3326, 0), new Tile(3167, 3286, 0))
	, new WebLine(ctx, new Tile(3167, 3286, 0), new Tile(3154, 3258, 0))
	, new WebLine(ctx, new Tile(3154, 3258, 0), new Tile(3190, 3244, 0))
	, new WebLine(ctx, new Tile(3154, 3258, 0), new Tile(3103, 3250, 0))
	, new WebLine(ctx, new Tile(3093, 3242, 0), new Tile(3103, 3250, 0))
	, new WebLine(ctx, new Tile(2945, 3438, 0), new Tile(2968, 3416, 0))
	, new WebLine(ctx, new Tile(3051, 3457, 0), new Tile(3042, 3471, 0))
	, new WebLine(ctx, new Tile(3052, 3490, 0), new Tile(3042, 3471, 0))
	, new WebLine(ctx, new Tile(3042, 3471, 0), new Tile(3016, 3457, 0))
	, new WebLine(ctx, new Tile(3051, 3457, 0), new Tile(3095, 3450, 0))
	, new WebLine(ctx, new Tile(3076, 3342, 0), new Tile(3076, 3385, 0))
	, new WebLine(ctx, new Tile(3046, 3247, 0), new Tile(3060, 3275, 0))
	, new WebLine(ctx, new Tile(3033, 3276, 0), new Tile(3060, 3275, 0))
	, new WebLine(ctx, new Tile(3055, 3322, 0), new Tile(3076, 3342, 0))
	, new WebLine(ctx, new Tile(3055, 3322, 0), new Tile(3055, 3307, 0))
	, new WebLine(ctx, new Tile(3060, 3275, 0), new Tile(3074, 3274, 0))
	, new WebLine(ctx, new Tile(3080, 3326, 0), new Tile(3076, 3342, 0))
	, new WebLine(ctx, new Tile(3215, 3218, 0), new Tile(3234, 3219, 0))
	, new WebLine(ctx, new Tile(3215, 3218, 0), new Tile(3206, 3209, 0))
	*/

};
}
