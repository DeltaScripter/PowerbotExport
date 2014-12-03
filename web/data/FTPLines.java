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
	
	new WebLine(ctx, new Tile(3070, 3276, 0),new Tile(3060, 3275, 0)),
	new WebLine(ctx, new Tile(3060, 3275, 0),new Tile(3060, 3275, 0)),
	new WebLine(ctx, new Tile(3060, 3275, 0),new Tile(3050, 3274, 0)),
	new WebLine(ctx, new Tile(3050, 3274, 0),new Tile(3038, 3276, 0)),
	new WebLine(ctx, new Tile(3038, 3276, 0),new Tile(3038, 3276, 0)),
	new WebLine(ctx, new Tile(3038, 3276, 0),new Tile(3028, 3276, 0)),
	new WebLine(ctx, new Tile(3028, 3276, 0),new Tile(3016, 3275, 0)),
	new WebLine(ctx, new Tile(3016, 3275, 0),new Tile(3007, 3277, 0)),
	new WebLine(ctx, new Tile(3007, 3277, 0),new Tile(3006, 3289, 0)),
	new WebLine(ctx, new Tile(3006, 3289, 0),new Tile(3006, 3300, 0)),
	new WebLine(ctx, new Tile(3006, 3300, 0),new Tile(3008, 3311, 0)),
	new WebLine(ctx, new Tile(3008, 3311, 0),new Tile(3007, 3321, 0)),
	new WebLine(ctx, new Tile(3007, 3321, 0),new Tile(3007, 3334, 0)),
	new WebLine(ctx, new Tile(3007, 3334, 0),new Tile(3007, 3345, 0)),
	new WebLine(ctx, new Tile(3007, 3345, 0),new Tile(3005, 3356, 0)),
	new WebLine(ctx, new Tile(3005, 3356, 0),new Tile(2997, 3363, 0)),
	new WebLine(ctx, new Tile(2997, 3363, 0),new Tile(2997, 3363, 0)),
	new WebLine(ctx, new Tile(2997, 3363, 0),new Tile(2989, 3370, 0)),
	new WebLine(ctx, new Tile(2989, 3370, 0),new Tile(2989, 3370, 0)),
	new WebLine(ctx, new Tile(2989, 3370, 0),new Tile(2981, 3377, 0)),
	new WebLine(ctx, new Tile(2981, 3377, 0),new Tile(2968, 3380, 0)),
	new WebLine(ctx, new Tile(2968, 3380, 0),new Tile(2967, 3387, 0)),
	new WebLine(ctx, new Tile(2967, 3387, 0),new Tile(2966, 3394, 0)),
	new WebLine(ctx, new Tile(2966, 3394, 0),new Tile(2966, 3394, 0)),
	new WebLine(ctx, new Tile(2966, 3394, 0),new Tile(2964, 3404, 0)),
	
	
	//the branches from flador to barbarian village
	new WebLine(ctx, new Tile(2964, 3404, 0),new Tile(2973, 3413, 0)),
	new WebLine(ctx, new Tile(2973, 3413, 0),new Tile(2978, 3416, 0)),
	new WebLine(ctx, new Tile(2978, 3416, 0),new Tile(2985, 3419, 0)),
	new WebLine(ctx, new Tile(2985, 3419, 0),new Tile(2988, 3428, 0)),
	new WebLine(ctx, new Tile(2988, 3428, 0),new Tile(2994, 3432, 0)),
	new WebLine(ctx, new Tile(2994, 3432, 0),new Tile(3005, 3432, 0)),
	new WebLine(ctx, new Tile(3005, 3432, 0),new Tile(3014, 3430, 0)),
	new WebLine(ctx, new Tile(3014, 3430, 0),new Tile(3021, 3426, 0)),
	new WebLine(ctx, new Tile(3021, 3426, 0),new Tile(3031, 3427, 0)),
	new WebLine(ctx, new Tile(3031, 3427, 0),new Tile(3039, 3423, 0)),
	new WebLine(ctx, new Tile(3039, 3423, 0),new Tile(3045, 3417, 0)),
	new WebLine(ctx, new Tile(3045, 3417, 0),new Tile(3055, 3411, 0)),
	new WebLine(ctx, new Tile(3055, 3411, 0),new Tile(3062, 3418, 0)),
	new WebLine(ctx, new Tile(3062, 3418, 0),new Tile(3074, 3417, 0)),
	new WebLine(ctx, new Tile(3074, 3417, 0),new Tile(3085, 3420, 0)),
	
	
	
	
	new WebLine(ctx, new Tile(2964, 3404, 0),new Tile(2964, 3404, 0)),
	new WebLine(ctx, new Tile(2964, 3404, 0),new Tile(2957, 3414, 0)),
	new WebLine(ctx, new Tile(2957, 3414, 0),new Tile(2950, 3420, 0)),
	new WebLine(ctx, new Tile(2950, 3420, 0),new Tile(2950, 3420, 0)),
	new WebLine(ctx, new Tile(2950, 3420, 0),new Tile(2948, 3429, 0)),
	new WebLine(ctx, new Tile(2948, 3429, 0),new Tile(2948, 3439, 0)),
	new WebLine(ctx, new Tile(2948, 3439, 0),new Tile(2947, 3449, 0)),
	new WebLine(ctx, new Tile(2947, 3449, 0),new Tile(2939, 3450, 0)),
	new WebLine(ctx, new Tile(2939, 3450, 0),new Tile(2930, 3450, 0)),
	new WebLine(ctx, new Tile(2930, 3450, 0),new Tile(2920, 3455, 0)),
	new WebLine(ctx, new Tile(2920, 3455, 0),new Tile(2908, 3455, 0)),
	new WebLine(ctx, new Tile(2908, 3455, 0),new Tile(2896, 3455, 0)),
	new WebLine(ctx, new Tile(2896, 3455, 0),new Tile(2887, 3444, 0)),
	new WebLine(ctx, new Tile(2887, 3444, 0),new Tile(2872, 3441, 0)),
	new WebLine(ctx, new Tile(2872, 3441, 0),new Tile(2872, 3441, 0)),
	new WebLine(ctx, new Tile(2872, 3441, 0),new Tile(2873, 3449, 0)),
	new WebLine(ctx, new Tile(2873, 3449, 0),new Tile(2872, 3460, 0)),
	new WebLine(ctx, new Tile(2872, 3460, 0),new Tile(2872, 3460, 0)),
	new WebLine(ctx, new Tile(2872, 3460, 0),new Tile(2869, 3469, 0)),
	new WebLine(ctx, new Tile(2869, 3469, 0),new Tile(2867, 3480, 0)),
	new WebLine(ctx, new Tile(2867, 3480, 0),new Tile(2867, 3480, 0)),
	new WebLine(ctx, new Tile(2867, 3480, 0),new Tile(2870, 3488, 0)),
	new WebLine(ctx, new Tile(2870, 3488, 0),new Tile(2875, 3498, 0)),
	new WebLine(ctx, new Tile(2875, 3498, 0),new Tile(2877, 3508, 0)),
	new WebLine(ctx, new Tile(2877, 3508, 0),new Tile(2877, 3517, 0)),
	new WebLine(ctx, new Tile(2877, 3517, 0),new Tile(2877, 3517, 0)),
	new WebLine(ctx, new Tile(2877, 3517, 0),new Tile(2870, 3525, 0)),
	new WebLine(ctx, new Tile(2870, 3525, 0),new Tile(2870, 3525, 0)),
	new WebLine(ctx, new Tile(2870, 3525, 0),new Tile(2861, 3524, 0)),
	new WebLine(ctx, new Tile(2861, 3524, 0),new Tile(2850, 3524, 0)),
	new WebLine(ctx, new Tile(2850, 3524, 0),new Tile(2841, 3526, 0)),
	new WebLine(ctx, new Tile(2841, 3526, 0),new Tile(2841, 3526, 0)),
	new WebLine(ctx, new Tile(2841, 3526, 0),new Tile(2833, 3532, 0)),
	new WebLine(ctx, new Tile(2833, 3532, 0),new Tile(2827, 3536, 0)),
	new WebLine(ctx, new Tile(2827, 3536, 0),new Tile(2827, 3536, 0)),
	new WebLine(ctx, new Tile(2827, 3536, 0),new Tile(2823, 3528, 0)),
	new WebLine(ctx, new Tile(2823, 3528, 0),new Tile(2823, 3528, 0)),
	new WebLine(ctx, new Tile(2823, 3528, 0),new Tile(2812, 3522, 0)),
	new WebLine(ctx, new Tile(2812, 3522, 0),new Tile(2803, 3517, 0)),
	new WebLine(ctx, new Tile(2803, 3517, 0),new Tile(2803, 3517, 0)),
	new WebLine(ctx, new Tile(2803, 3517, 0),new Tile(2796, 3512, 0)),
	new WebLine(ctx, new Tile(2796, 3512, 0),new Tile(2795, 3502, 0)),
	new WebLine(ctx, new Tile(2795, 3502, 0),new Tile(2798, 3495, 0)),
	new WebLine(ctx, new Tile(2798, 3495, 0),new Tile(2798, 3495, 0)),
	new WebLine(ctx, new Tile(2798, 3495, 0),new Tile(2807, 3496, 0)),
	new WebLine(ctx, new Tile(2807, 3496, 0),new Tile(2814, 3499, 0)),
	new WebLine(ctx, new Tile(2814, 3499, 0),new Tile(2824, 3499, 0)),
	new WebLine(ctx, new Tile(2824, 3499, 0),new Tile(2824, 3499, 0)),
	new WebLine(ctx, new Tile(2824, 3499, 0),new Tile(2829, 3494, 0)),
	new WebLine(ctx, new Tile(2829, 3494, 0),new Tile(2833, 3487, 0)),
	new WebLine(ctx, new Tile(2833, 3487, 0),new Tile(2833, 3487, 0)),
	new WebLine(ctx, new Tile(2833, 3487, 0),new Tile(2835, 3479, 0)),
	new WebLine(ctx, new Tile(2835, 3479, 0),new Tile(2835, 3479, 0)),
	new WebLine(ctx, new Tile(2835, 3479, 0),new Tile(2839, 3470, 0)),
	new WebLine(ctx, new Tile(2839, 3470, 0),new Tile(2843, 3462, 0)),
	new WebLine(ctx, new Tile(2843, 3462, 0),new Tile(2847, 3451, 0)),
	new WebLine(ctx, new Tile(2847, 3451, 0),new Tile(2854, 3445, 0)),
	new WebLine(ctx, new Tile(2854, 3445, 0),new Tile(2859, 3441, 0)),
	new WebLine(ctx, new Tile(2859, 3441, 0),new Tile(2850, 3437, 0)),
	new WebLine(ctx, new Tile(2850, 3437, 0),new Tile(2841, 3436, 0)),
	new WebLine(ctx, new Tile(2841, 3436, 0),new Tile(2835, 3435, 0)),
	new WebLine(ctx, new Tile(2835, 3435, 0),new Tile(2827, 3438, 0)),
	new WebLine(ctx, new Tile(2827, 3438, 0),new Tile(2827, 3438, 0)),
	new WebLine(ctx, new Tile(2827, 3438, 0),new Tile(2820, 3438, 0)),
	new WebLine(ctx, new Tile(2820, 3438, 0),new Tile(2813, 3445, 0)),
	new WebLine(ctx, new Tile(2813, 3445, 0),new Tile(2803, 3445, 0)),
	new WebLine(ctx, new Tile(2803, 3445, 0),new Tile(2804, 3436, 0)),
	new WebLine(ctx, new Tile(2804, 3436, 0),new Tile(2804, 3433, 0)),
	
	
	new WebLine(ctx, new Tile(2804, 3433, 0),new Tile(2795, 3432, 0)),
	new WebLine(ctx, new Tile(2795, 3432, 0),new Tile(2789, 3437, 0)),
	new WebLine(ctx, new Tile(2789, 3437, 0),new Tile(2783, 3443, 0)),
	new WebLine(ctx, new Tile(2783, 3443, 0),new Tile(2777, 3449, 0)),
	new WebLine(ctx, new Tile(2777, 3449, 0),new Tile(2772, 3454, 0)),
	new WebLine(ctx, new Tile(2772, 3454, 0),new Tile(2767, 3460, 0)),
	new WebLine(ctx, new Tile(2767, 3460, 0),new Tile(2763, 3467, 0)),
	new WebLine(ctx, new Tile(2763, 3467, 0),new Tile(2757, 3474, 0)),
	new WebLine(ctx, new Tile(2757, 3474, 0),new Tile(2752, 3477, 0)),
	new WebLine(ctx, new Tile(2752, 3477, 0),new Tile(2743, 3479, 0)),
	new WebLine(ctx, new Tile(2743, 3479, 0),new Tile(2733, 3484, 0)),
	new WebLine(ctx, new Tile(2733, 3484, 0),new Tile(2724, 3483, 0)),
	new WebLine(ctx, new Tile(2724, 3483, 0),new Tile(2714, 3484, 0)),
	new WebLine(ctx, new Tile(2714, 3484, 0),new Tile(2706, 3483, 0)),
	new WebLine(ctx, new Tile(2706, 3483, 0),new Tile(2698, 3483, 0)),
	new WebLine(ctx, new Tile(2698, 3483, 0),new Tile(2689, 3484, 0)),
	new WebLine(ctx, new Tile(2689, 3484, 0),new Tile(2683, 3482, 0)),
	
	new WebLine(ctx, new Tile(2683, 3482, 0),new Tile(2679, 3478, 0)),
	new WebLine(ctx, new Tile(2679, 3478, 0),new Tile(2680, 3470, 0)),
	new WebLine(ctx, new Tile(2680, 3470, 0),new Tile(2673, 3465, 0)),
	new WebLine(ctx, new Tile(2673, 3465, 0),new Tile(2669, 3457, 0)),
	new WebLine(ctx, new Tile(2669, 3457, 0),new Tile(2665, 3450, 0)),
	new WebLine(ctx, new Tile(2665, 3450, 0),new Tile(2658, 3445, 0)),
	new WebLine(ctx, new Tile(2658, 3445, 0),new Tile(2652, 3439, 0)),
	new WebLine(ctx, new Tile(2652, 3439, 0),new Tile(2647, 3433, 0)),
	new WebLine(ctx, new Tile(2647, 3433, 0),new Tile(2645, 3426, 0)),
	new WebLine(ctx, new Tile(2645, 3426, 0),new Tile(2646, 3418, 0)),
	new WebLine(ctx, new Tile(2646, 3418, 0),new Tile(2646, 3408, 0)),
	new WebLine(ctx, new Tile(2646, 3408, 0),new Tile(2643, 3400, 0)),
	new WebLine(ctx, new Tile(2643, 3400, 0),new Tile(2634, 3401, 0)),
	new WebLine(ctx, new Tile(2634, 3401, 0),new Tile(2630, 3396, 0)),
	new WebLine(ctx, new Tile(2630, 3396, 0),new Tile(2624, 3390, 0)),
	new WebLine(ctx, new Tile(2624, 3390, 0),new Tile(2618, 3385, 0)),
	new WebLine(ctx, new Tile(2618, 3385, 0),new Tile(2612, 3381, 0)),
	new WebLine(ctx, new Tile(2612, 3381, 0),new Tile(2603, 3381, 0)),
	new WebLine(ctx, new Tile(2603, 3381, 0),new Tile(2594, 3382, 0)),
	new WebLine(ctx, new Tile(2594, 3382, 0),new Tile(2584, 3383, 0)),
	
	
	new WebLine(ctx, new Tile(2584, 3383, 0),new Tile(2585, 3374, 0)),
	new WebLine(ctx, new Tile(2585, 3374, 0),new Tile(2582, 3368, 0)),
	new WebLine(ctx, new Tile(2582, 3368, 0),new Tile(2581, 3359, 0)),
	new WebLine(ctx, new Tile(2581, 3359, 0),new Tile(2582, 3351, 0)),
	new WebLine(ctx, new Tile(2582, 3351, 0),new Tile(2588, 3344, 0)),
	new WebLine(ctx, new Tile(2588, 3344, 0),new Tile(2586, 3334, 0)),
	new WebLine(ctx, new Tile(2586, 3334, 0),new Tile(2582, 3329, 0)),
	new WebLine(ctx, new Tile(2582, 3329, 0),new Tile(2581, 3322, 0)),
	new WebLine(ctx, new Tile(2581, 3322, 0),new Tile(2577, 3314, 0)),
	new WebLine(ctx, new Tile(2577, 3314, 0),new Tile(2569, 3313, 0)),
	new WebLine(ctx, new Tile(2569, 3313, 0),new Tile(2565, 3307, 0)),
	new WebLine(ctx, new Tile(2565, 3307, 0),new Tile(2563, 3299, 0)),
	new WebLine(ctx, new Tile(2563, 3299, 0),new Tile(2563, 3291, 0)),
	new WebLine(ctx, new Tile(2563, 3291, 0),new Tile(2566, 3284, 0)),
	new WebLine(ctx, new Tile(2566, 3284, 0),new Tile(2573, 3281, 0)),
	new WebLine(ctx, new Tile(2573, 3281, 0),new Tile(2581, 3279, 0)),
	new WebLine(ctx, new Tile(2581, 3279, 0),new Tile(2589, 3283, 0)),
	new WebLine(ctx, new Tile(2589, 3283, 0),new Tile(2592, 3293, 0)),
	new WebLine(ctx, new Tile(2592, 3293, 0),new Tile(2597, 3296, 0)),
	new WebLine(ctx, new Tile(2597, 3296, 0),new Tile(2606, 3295, 0)),
	new WebLine(ctx, new Tile(2606, 3295, 0),new Tile(2604, 3289, 0)),
	new WebLine(ctx, new Tile(2604, 3289, 0),new Tile(2607, 3282, 0)),
	new WebLine(ctx, new Tile(2607, 3282, 0),new Tile(2607, 3275, 0)),
	new WebLine(ctx, new Tile(2607, 3275, 0),new Tile(2610, 3268, 0)),
	new WebLine(ctx, new Tile(2610, 3268, 0),new Tile(2603, 3263, 0)),
	new WebLine(ctx, new Tile(2603, 3263, 0),new Tile(2597, 3257, 0)),
	new WebLine(ctx, new Tile(2597, 3257, 0),new Tile(2593, 3250, 0)),
	new WebLine(ctx, new Tile(2593, 3250, 0),new Tile(2586, 3241, 0)),
	new WebLine(ctx, new Tile(2586, 3241, 0),new Tile(2583, 3232, 0)),
	
	new WebLine(ctx, new Tile(2583, 3232, 0),new Tile(2579, 3223, 0)),
	new WebLine(ctx, new Tile(2579, 3223, 0),new Tile(2574, 3223, 0)),
	new WebLine(ctx, new Tile(2574, 3223, 0),new Tile(2566, 3219, 0)),
	new WebLine(ctx, new Tile(2566, 3219, 0),new Tile(2562, 3213, 0)),
	new WebLine(ctx, new Tile(2562, 3213, 0),new Tile(2553, 3209, 0)),
	new WebLine(ctx, new Tile(2553, 3209, 0),new Tile(2547, 3210, 0)),
	new WebLine(ctx, new Tile(2547, 3210, 0),new Tile(2541, 3207, 0)),
	new WebLine(ctx, new Tile(2541, 3207, 0),new Tile(2535, 3209, 0)),
	new WebLine(ctx, new Tile(2535, 3209, 0),new Tile(2530, 3208, 0)),
	new WebLine(ctx, new Tile(2530, 3208, 0),new Tile(2527, 3209, 0)),
	new WebLine(ctx, new Tile(2527, 3209, 0),new Tile(2519, 3204, 0)),
	new WebLine(ctx, new Tile(2519, 3204, 0),new Tile(2512, 3204, 0)),
	new WebLine(ctx, new Tile(2512, 3204, 0),new Tile(2508, 3202, 0)),
	new WebLine(ctx, new Tile(2508, 3202, 0),new Tile(2504, 3194, 0)),
	new WebLine(ctx, new Tile(2504, 3194, 0),new Tile(2505, 3190, 0)),

	new WebLine(ctx, new Tile(2505, 3190, 0),new Tile(2522, 3187, 0)),
	new WebLine(ctx, new Tile(2522, 3187, 0),new Tile(2522, 3187, 0)),
	new WebLine(ctx, new Tile(2522, 3187, 0),new Tile(2524, 3180, 0)),
	new WebLine(ctx, new Tile(2524, 3180, 0),new Tile(2533, 3179, 0)),
	new WebLine(ctx, new Tile(2533, 3179, 0),new Tile(2540, 3177, 0)),
	new WebLine(ctx, new Tile(2540, 3177, 0),new Tile(2550, 3173, 0)),
	new WebLine(ctx, new Tile(2550, 3173, 0),new Tile(2550, 3167, 0)),
	new WebLine(ctx, new Tile(2550, 3167, 0),new Tile(2543, 3155, 0)),
	new WebLine(ctx, new Tile(2543, 3155, 0),new Tile(2543, 3155, 0)),
	new WebLine(ctx, new Tile(2543, 3155, 0),new Tile(2535, 3155, 0)),

	
	
	
	new WebLine(ctx, new Tile(2584, 3383, 0),new Tile(2579, 3389, 0)),
	new WebLine(ctx, new Tile(2579, 3389, 0),new Tile(2576, 3397, 0)),
	new WebLine(ctx, new Tile(2576, 3397, 0),new Tile(2573, 3405, 0)),
	new WebLine(ctx, new Tile(2573, 3405, 0),new Tile(2569, 3410, 0)),
	new WebLine(ctx, new Tile(2569, 3410, 0),new Tile(2567, 3418, 0)),
	new WebLine(ctx, new Tile(2567, 3418, 0),new Tile(2565, 3426, 0)),
	
	
	new WebLine(ctx, new Tile(2565, 3426, 0),new Tile(2562, 3427, 0)),
	new WebLine(ctx, new Tile(2562, 3427, 0),new Tile(2556, 3430, 0)),
	new WebLine(ctx, new Tile(2556, 3430, 0),new Tile(2549, 3433, 0)),
	new WebLine(ctx, new Tile(2549, 3433, 0),new Tile(2540, 3432, 0)),
	new WebLine(ctx, new Tile(2540, 3432, 0),new Tile(2533, 3428, 0)),
	new WebLine(ctx, new Tile(2533, 3428, 0),new Tile(2528, 3432, 0)),
	new WebLine(ctx, new Tile(2528, 3432, 0),new Tile(2523, 3431, 0)),
	
	
	new WebLine(ctx, new Tile(2565, 3426, 0),new Tile(2565, 3436, 0)),
	new WebLine(ctx, new Tile(2565, 3436, 0),new Tile(2567, 3445, 0)),
	new WebLine(ctx, new Tile(2567, 3445, 0),new Tile(2567, 3453, 0)),
	new WebLine(ctx, new Tile(2567, 3453, 0),new Tile(2557, 3452, 0)),
	
	new WebLine(ctx, new Tile(2557, 3452, 0),new Tile(2556, 3458, 0)),
	new WebLine(ctx, new Tile(2556, 3458, 0),new Tile(2553, 3465, 0)),
	new WebLine(ctx, new Tile(2553, 3465, 0),new Tile(2551, 3472, 0)),
	new WebLine(ctx, new Tile(2551, 3472, 0),new Tile(2544, 3477, 0)),
	new WebLine(ctx, new Tile(2544, 3477, 0),new Tile(2539, 3474, 0)),
	new WebLine(ctx, new Tile(2539, 3474, 0),new Tile(2531, 3480, 0)),
	new WebLine(ctx, new Tile(2531, 3480, 0),new Tile(2532, 3488, 0)),
	new WebLine(ctx, new Tile(2532, 3488, 0),new Tile(2529, 3495, 0)),


	
	new WebLine(ctx, new Tile(3167, 3289, 0),new Tile(3166, 3293, 0)),
	new WebLine(ctx, new Tile(3166, 3293, 0),new Tile(3166, 3298, 0)),
	new WebLine(ctx, new Tile(3166, 3298, 0),new Tile(3166, 3302, 0)),
	
	new WebLine(ctx, new Tile(3235, 3217, 0),new Tile(3230, 3218, 0)),
	new WebLine(ctx, new Tile(3230, 3218, 0),new Tile(3225, 3218, 0)),
	new WebLine(ctx, new Tile(3225, 3218, 0),new Tile(3220, 3218, 0)),
	new WebLine(ctx, new Tile(3220, 3218, 0),new Tile(3215, 3219, 0)),
	new WebLine(ctx, new Tile(3215, 3219, 0),new Tile(3206, 3227, 0)),


};
}
