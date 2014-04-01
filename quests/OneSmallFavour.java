/*package quests;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.ChatOptions;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class OneSmallFavour extends Node{

	public final Tile[] pathToPort = new Tile[] {//Path to the port to take a trip to Karamja 
			new Tile(3232, 3222, 0), new Tile(3229, 3226, 0), new Tile(3226, 3230, 0), 
			new Tile(3223, 3234, 0), new Tile(3222, 3239, 0), new Tile(3220, 3244, 0), 
			new Tile(3215, 3246, 0), new Tile(3210, 3246, 0), new Tile(3205, 3248, 0), 
			new Tile(3200, 3248, 0), new Tile(3195, 3246, 0), new Tile(3191, 3243, 0), 
			new Tile(3186, 3243, 0), new Tile(3181, 3244, 0), new Tile(3176, 3244, 0), 
			new Tile(3172, 3241, 0), new Tile(3167, 3239, 0), new Tile(3162, 3239, 0), 
			new Tile(3157, 3240, 0), new Tile(3153, 3237, 0), new Tile(3148, 3235, 0), 
			new Tile(3143, 3233, 0), new Tile(3139, 3230, 0), new Tile(3134, 3228, 0), 
			new Tile(3129, 3225, 0), new Tile(3124, 3223, 0), new Tile(3120, 3220, 0), 
			new Tile(3115, 3221, 0), new Tile(3111, 3224, 0), new Tile(3110, 3229, 0), 
			new Tile(3108, 3234, 0), new Tile(3106, 3239, 0), new Tile(3105, 3244, 0), 
			new Tile(3104, 3249, 0), new Tile(3104, 3254, 0), new Tile(3104, 3259, 0), 
			new Tile(3104, 3264, 0), new Tile(3104, 3269, 0), new Tile(3104, 3274, 0), 
			new Tile(3104, 3279, 0), new Tile(3107, 3283, 0), new Tile(3109, 3288, 0), 
			new Tile(3110, 3293, 0), new Tile(3108, 3298, 0), new Tile(3103, 3299, 0), 
			new Tile(3099, 3296, 0), new Tile(3094, 3294, 0), new Tile(3089, 3291, 0), 
			new Tile(3085, 3288, 0), new Tile(3079, 3287, 0), new Tile(3076, 3283, 0), 
			new Tile(3073, 3279, 0), new Tile(3069, 3276, 0), new Tile(3064, 3276, 0), 
			new Tile(3062, 3271, 0), new Tile(3062, 3266, 0), new Tile(3062, 3261, 0), 
			new Tile(3060, 3256, 0), new Tile(3055, 3255, 0), new Tile(3051, 3252, 0), 
			new Tile(3051, 3247, 0), new Tile(3046, 3247, 0), new Tile(3041, 3246, 0), 
			new Tile(3041, 3241, 0), new Tile(3040, 3236, 0), new Tile(3036, 3233, 0), 
			new Tile(3031, 3233, 0), new Tile(3026, 3233, 0), new Tile(3024, 3228, 0), 
			new Tile(3025, 3223, 0), new Tile(3025, 3218, 0), new Tile(3020, 3217, 0), 
			new Tile(3015, 3217, 0), new Tile(3010, 3217, 0), new Tile(3015, 3216, 0), 
			new Tile(3020, 3215, 0), new Tile(3025, 3215, 0), new Tile(3028, 3215, 0) };
	
	public final Tile[] pathToMusaStart = new Tile[] {//Brings you to the area where we started Shilo Village 
			new Tile(2954, 3146, 0), new Tile(2949, 3146, 0), new Tile(2944, 3145, 0), 
			new Tile(2939, 3145, 0), new Tile(2934, 3147, 0), new Tile(2930, 3150, 0), 
			new Tile(2925, 3151, 0), new Tile(2920, 3151, 0), new Tile(2915, 3153, 0), 
			new Tile(2910, 3153, 0), new Tile(2905, 3153, 0), new Tile(2900, 3154, 0), 
			new Tile(2895, 3156, 0), new Tile(2890, 3154, 0), new Tile(2885, 3154, 0), 
			new Tile(2880, 3155, 0), new Tile(2875, 3157, 0), new Tile(2871, 3160, 0), 
			new Tile(2868, 3164, 0), new Tile(2868, 3169, 0), new Tile(2868, 3174, 0), 
			new Tile(2866, 3179, 0), new Tile(2864, 3184, 0), new Tile(2859, 3184, 0), 
			new Tile(2855, 3187, 0), new Tile(2852, 3191, 0), new Tile(2848, 3194, 0), 
			new Tile(2844, 3197, 0), new Tile(2839, 3198, 0), new Tile(2834, 3195, 0), 
			new Tile(2832, 3190, 0), new Tile(2828, 3187, 0), new Tile(2823, 3185, 0), 
			new Tile(2818, 3183, 0), new Tile(2813, 3183, 0), new Tile(2808, 3181, 0), 
			new Tile(2803, 3180, 0), new Tile(2798, 3178, 0), new Tile(2793, 3178, 0), 
			new Tile(2788, 3178, 0), new Tile(2783, 3178, 0), new Tile(2778, 3180, 0), 
			new Tile(2774, 3183, 0), new Tile(2769, 3183, 0), new Tile(2764, 3183, 0), 
			new Tile(2760, 3180, 0), new Tile(2756, 3177, 0), new Tile(2752, 3174, 0), 
			new Tile(2748, 3170, 0), new Tile(2747, 3165, 0), new Tile(2746, 3160, 0), 
			new Tile(2747, 3155, 0), new Tile(2750, 3151, 0), new Tile(2755, 3149, 0), 
			new Tile(2760, 3146, 0), new Tile(2765, 3144, 0), new Tile(2769, 3141, 0), 
			new Tile(2774, 3139, 0), new Tile(2777, 3135, 0), new Tile(2781, 3132, 0), 
			new Tile(2782, 3127, 0), new Tile(2779, 3123, 0), new Tile(2776, 3119, 0), 
			new Tile(2776, 3114, 0), new Tile(2778, 3109, 0), new Tile(2780, 3104, 0), 
			new Tile(2783, 3100, 0), new Tile(2787, 3096, 0), new Tile(2791, 3093, 0), 
			new Tile(2794, 3089, 0), new Tile(2799, 3087, 0), new Tile(2803, 3083, 0), 
			new Tile(2807, 3079, 0), new Tile(2811, 3076, 0), new Tile(2815, 3072, 0), 
			new Tile(2819, 3068, 0), new Tile(2823, 3065, 0), new Tile(2827, 3062, 0), 
			new Tile(2830, 3058, 0), new Tile(2831, 3053, 0), new Tile(2833, 3048, 0), 
			new Tile(2835, 3043, 0), new Tile(2836, 3038, 0), new Tile(2836, 3033, 0), 
			new Tile(2835, 3028, 0), new Tile(2832, 3024, 0), new Tile(2829, 3020, 0), 
			new Tile(2826, 3016, 0), new Tile(2823, 3012, 0), new Tile(2819, 3009, 0), 
			new Tile(2815, 3006, 0), new Tile(2814, 3001, 0), new Tile(2814, 2996, 0), 
			new Tile(2814, 2991, 0), new Tile(2814, 2986, 0), new Tile(2813, 2981, 0), 
			new Tile(2809, 2978, 0), new Tile(2805, 2975, 0), new Tile(2800, 2972, 0), 
			new Tile(2795, 2971, 0), new Tile(2795, 2966, 0), new Tile(2795, 2961, 0), 
			new Tile(2797, 2956, 0), new Tile(2801, 2953, 0), new Tile(2805, 2949, 0), 
			new Tile(2810, 2947, 0), new Tile(2815, 2945, 0), new Tile(2820, 2944, 0), 
			new Tile(2825, 2943, 0), new Tile(2830, 2943, 0), new Tile(2835, 2943, 0), 
			new Tile(2840, 2943, 0), new Tile(2845, 2943, 0), new Tile(2850, 2943, 0), 
			new Tile(2855, 2942, 0), new Tile(2860, 2941, 0), new Tile(2865, 2941, 0), 
			new Tile(2870, 2941, 0), new Tile(2875, 2942, 0), new Tile(2879, 2945, 0), 
			new Tile(2882, 2949, 0), new Tile(2879, 2953, 0) };
	
	public final Tile[] pathToYanni = new Tile[] { 
			new Tile(2865, 2953, 0), new Tile(2866, 2958, 0), new Tile(2861, 2960, 0), 
			new Tile(2856, 2961, 0), new Tile(2851, 2962, 0), new Tile(2846, 2964, 0), 
			new Tile(2841, 2966, 0), new Tile(2836, 2966, 0), new Tile(2832, 2969, 0), 
			new Tile(2830, 2974, 0), new Tile(2830, 2979, 0), new Tile(2832, 2984, 0), 
			new Tile(2833, 2985, 0) };
	
	public final Tile[] pathToPortSarimHatchetShop = new Tile[] { 
			new Tile(3105, 3299, 0), new Tile(3100, 3298, 0), new Tile(3095, 3296, 0), 
			new Tile(3093, 3291, 0), new Tile(3089, 3287, 0), new Tile(3085, 3284, 0), 
			new Tile(3080, 3282, 0), new Tile(3076, 3279, 0), new Tile(3072, 3276, 0), 
			new Tile(3067, 3276, 0), new Tile(3063, 3272, 0), new Tile(3063, 3267, 0), 
			new Tile(3063, 3262, 0), new Tile(3062, 3257, 0), new Tile(3059, 3253, 0), 
			new Tile(3055, 3250, 0), new Tile(3051, 3247, 0), new Tile(3046, 3246, 0), 
			new Tile(3042, 3243, 0), new Tile(3039, 3239, 0), new Tile(3036, 3235, 0), 
			new Tile(3031, 3235, 0), new Tile(3027, 3238, 0), new Tile(3024, 3234, 0), 
			new Tile(3024, 3229, 0), new Tile(3025, 3224, 0), new Tile(3025, 3219, 0), 
			new Tile(3020, 3217, 0), new Tile(3015, 3217, 0), new Tile(3010, 3216, 0), 
			new Tile(3015, 3216, 0), new Tile(3020, 3217, 0), new Tile(3025, 3217, 0), 
			new Tile(3026, 3222, 0), new Tile(3025, 3227, 0), new Tile(3025, 3232, 0), 
			new Tile(3025, 3237, 0), new Tile(3024, 3242, 0), new Tile(3025, 3243, 0) };
	
	public final Tile[] pathToAggie = new Tile[] { 
			new Tile(3103, 3299, 0), new Tile(3108, 3299, 0), new Tile(3108, 3294, 0), 
			new Tile(3107, 3289, 0), new Tile(3105, 3284, 0), new Tile(3105, 3279, 0), 
			new Tile(3103, 3274, 0), new Tile(3102, 3269, 0), new Tile(3102, 3264, 0), 
			new Tile(3097, 3262, 0), new Tile(3092, 3262, 0), new Tile(3088, 3259, 0) };
	
	public final Tile[] pathToHam = new Tile[] { 
			new Tile(3232, 3221, 0), new Tile(3231, 3226, 0), new Tile(3228, 3230, 0), 
			new Tile(3225, 3234, 0), new Tile(3222, 3238, 0), new Tile(3220, 3243, 0), 
			new Tile(3217, 3247, 0), new Tile(3212, 3248, 0), new Tile(3207, 3248, 0), 
			new Tile(3202, 3247, 0), new Tile(3197, 3245, 0), new Tile(3192, 3244, 0), 
			new Tile(3187, 3244, 0), new Tile(3182, 3244, 0), new Tile(3178, 3247, 0), 
			new Tile(3173, 3247, 0), new Tile(3169, 3244, 0), new Tile(3165, 3247, 0), 
			new Tile(3164, 3250, 0) };
	
	public final Tile[] pathToFredTheFarmer = new Tile[] { 
			new Tile(3104, 3298, 0), new Tile(3109, 3298, 0), new Tile(3114, 3298, 0), 
			new Tile(3119, 3298, 0), new Tile(3124, 3298, 0), new Tile(3129, 3298, 0), 
			new Tile(3134, 3298, 0), new Tile(3139, 3296, 0), new Tile(3144, 3295, 0), 
			new Tile(3149, 3294, 0), new Tile(3154, 3292, 0), new Tile(3158, 3289, 0), 
			new Tile(3162, 3286, 0), new Tile(3167, 3285, 0), new Tile(3172, 3285, 0), 
			new Tile(3177, 3285, 0), new Tile(3182, 3284, 0), new Tile(3186, 3281, 0) };
	
	public final Tile[] pathToSethTheFarmer = new Tile[] { 
			new Tile(3232, 3221, 0), new Tile(3232, 3226, 0), new Tile(3237, 3227, 0), 
			new Tile(3242, 3226, 0), new Tile(3247, 3226, 0), new Tile(3252, 3228, 0), 
			new Tile(3256, 3231, 0), new Tile(3260, 3234, 0), new Tile(3261, 3239, 0), 
			new Tile(3257, 3242, 0), new Tile(3255, 3247, 0), new Tile(3252, 3251, 0), 
			new Tile(3249, 3255, 0), new Tile(3248, 3260, 0), new Tile(3248, 3265, 0), 
			new Tile(3246, 3270, 0), new Tile(3243, 3274, 0), new Tile(3241, 3279, 0), 
			new Tile(3239, 3284, 0), new Tile(3237, 3289, 0), new Tile(3237, 3293, 0) };
	
	public final Tile[] pathToVarrokArmourShop = new Tile[] { 
			new Tile(3230, 3220, 0), new Tile(3234, 3223, 0), new Tile(3238, 3226, 0), 
			new Tile(3243, 3225, 0), new Tile(3248, 3225, 0), new Tile(3253, 3227, 0), 
			new Tile(3257, 3230, 0), new Tile(3259, 3235, 0), new Tile(3259, 3240, 0), 
			new Tile(3257, 3245, 0), new Tile(3254, 3249, 0), new Tile(3250, 3253, 0), 
			new Tile(3249, 3258, 0), new Tile(3248, 3263, 0), new Tile(3245, 3267, 0), 
			new Tile(3243, 3272, 0), new Tile(3240, 3277, 0), new Tile(3239, 3282, 0), 
			new Tile(3238, 3287, 0), new Tile(3238, 3292, 0), new Tile(3238, 3297, 0), 
			new Tile(3238, 3302, 0), new Tile(3238, 3307, 0), new Tile(3238, 3312, 0), 
			new Tile(3238, 3317, 0), new Tile(3238, 3322, 0), new Tile(3238, 3327, 0), 
			new Tile(3239, 3332, 0), new Tile(3239, 3337, 0), new Tile(3234, 3338, 0), 
			new Tile(3229, 3338, 0), new Tile(3224, 3338, 0), new Tile(3223, 3343, 0), 
			new Tile(3224, 3348, 0), new Tile(3224, 3353, 0), new Tile(3224, 3358, 0), 
			new Tile(3221, 3362, 0), new Tile(3218, 3366, 0), new Tile(3215, 3370, 0), 
			new Tile(3213, 3375, 0), new Tile(3212, 3380, 0), new Tile(3211, 3385, 0), 
			new Tile(3211, 3390, 0), new Tile(3211, 3395, 0), new Tile(3211, 3400, 0), 
			new Tile(3211, 3405, 0), new Tile(3210, 3410, 0), new Tile(3210, 3415, 0), 
			new Tile(3210, 3420, 0), new Tile(3212, 3425, 0), new Tile(3216, 3428, 0), 
			new Tile(3221, 3430, 0), new Tile(3226, 3433, 0), new Tile(3229, 3437, 0) };
	
	public final Tile[] pathToPotionShop = new Tile[] {
			new Tile(3214, 3376, 0), new Tile(3210, 3380, 0), new Tile(3209, 3385, 0), 
			new Tile(3210, 3390, 0), new Tile(3210, 3395, 0), new Tile(3210, 3400, 0), 
			new Tile(3211, 3405, 0), new Tile(3207, 3408, 0), new Tile(3202, 3408, 0), 
			new Tile(3199, 3404, 0), new Tile(3195, 3401, 0), new Tile(3190, 3400, 0), 
			new Tile(3191,3403, 0) };
	
	public final Tile[] pathToPotter = new Tile[] {
			new Tile(3213, 3377, 0), new Tile(3213, 3382, 0), new Tile(3213, 3387, 0), 
			new Tile(3211, 3392, 0), new Tile(3210, 3397, 0), new Tile(3210, 3402, 0), 
			new Tile(3210, 3407, 0), new Tile(3210, 3412, 0), new Tile(3209, 3417, 0), 
			new Tile(3208, 3422, 0), new Tile(3205, 3426, 0), new Tile(3200, 3428, 0), 
			new Tile(3195, 3430, 0), new Tile(3190, 3429, 0), new Tile(3185, 3428, 0), 
			new Tile(3180, 3428, 0), new Tile(3175, 3428, 0), new Tile(3171, 3425, 0), 
			new Tile(3167, 3422, 0), new Tile(3162, 3420, 0), new Tile(3156, 3419, 0), 
			new Tile(3151, 3418, 0), new Tile(3146, 3417, 0), new Tile(3141, 3416, 0), 
			new Tile(3136, 3416, 0), new Tile(3131, 3416, 0), new Tile(3126, 3416, 0), 
			new Tile(3121, 3417, 0), new Tile(3116, 3418, 0), new Tile(3111, 3419, 0), 
			new Tile(3106, 3420, 0), new Tile(3101, 3420, 0), new Tile(3096, 3420, 0), 
			new Tile(3091, 3419, 0), new Tile(3086, 3418, 0), new Tile(3085, 3415, 0), 
			new Tile(3083, 3410, 0), new Tile(3083, 3408, 0) };
	
	public final Tile[] pathToDwarvenMine = new Tile[] { 
			new Tile(2966, 3403, 0), new Tile(2966, 3408, 0), new Tile(2966, 3413, 0), 
			new Tile(2971, 3414, 0), new Tile(2976, 3415, 0), new Tile(2980, 3418, 0), 
			new Tile(2984, 3421, 0), new Tile(2987, 3425, 0), new Tile(2991, 3429, 0), 
			new Tile(2996, 3431, 0), new Tile(3001, 3431, 0), new Tile(3006, 3431, 0), 
			new Tile(3011, 3433, 0), new Tile(3013, 3438, 0), new Tile(3015, 3443, 0), 
			new Tile(3018, 3447, 0), new Tile(3019, 3448, 0) };
	
	public final Tile[] pathToSanfew = new Tile[] { 
			new Tile(2898, 3543, 0), new Tile(2898, 3538, 0), new Tile(2898, 3533, 0), 
			new Tile(2897, 3528, 0), new Tile(2897, 3523, 0), new Tile(2898, 3518, 0), 
			new Tile(2898, 3513, 0), new Tile(2901, 3509, 0), new Tile(2905, 3506, 0), 
			new Tile(2909, 3503, 0), new Tile(2914, 3501, 0), new Tile(2917, 3497, 0), 
			new Tile(2920, 3492, 0), new Tile(2922, 3487, 0), new Tile(2922, 3482, 0), 
			new Tile(2921, 3477, 0), new Tile(2921, 3472, 0), new Tile(2922, 3467, 0), 
			new Tile(2922, 3462, 0), new Tile(2922, 3456, 0), new Tile(2922, 3451, 0), 
			new Tile(2922, 3446, 0), new Tile(2922, 3441, 0), new Tile(2918, 3438, 0), 
			new Tile(2915, 3438, 0) };
	
	public final Tile[] pathToGnome = new Tile[] { 
			new Tile(2898, 3544, 0), new Tile(2898, 3539, 0), new Tile(2897, 3534, 0), 
			new Tile(2897, 3529, 0), new Tile(2898, 3524, 0), new Tile(2899, 3519, 0), 
			new Tile(2900, 3514, 0), new Tile(2897, 3510, 0), new Tile(2895, 3505, 0), 
			new Tile(2893, 3500, 0), new Tile(2892, 3495, 0), new Tile(2892, 3490, 0), 
			new Tile(2892, 3485, 0), new Tile(2892, 3480, 0), new Tile(2892, 3475, 0), 
			new Tile(2892, 3470, 0), new Tile(2892, 3465, 0), new Tile(2892, 3460, 0), 
			new Tile(2890, 3455, 0), new Tile(2885, 3452, 0), new Tile(2884, 3447, 0), 
			new Tile(2884, 3442, 0), new Tile(2883, 3437, 0), new Tile(2882, 3432, 0), 
			new Tile(2881, 3427, 0), new Tile(2876, 3425, 0), new Tile(2872, 3429, 0), 
			new Tile(2870, 3434, 0), new Tile(2867, 3438, 0), new Tile(2865, 3443, 0), 
			new Tile(2864, 3448, 0), new Tile(2864, 3453, 0), new Tile(2864, 3458, 0), 
			new Tile(2859, 3460, 0), new Tile(2856, 3465, 0), new Tile(2854, 3470, 0), 
			new Tile(2852, 3475, 0), new Tile(2852, 3480, 0), new Tile(2853, 3485, 0), 
			new Tile(2854, 3490, 0), new Tile(2855, 3495, 0), new Tile(2855, 3500, 0), 
			new Tile(2854, 3505, 0), new Tile(2850, 3508, 0), new Tile(2845, 3508, 0), 
			new Tile(2840, 3508, 0), new Tile(2837, 3504, 0), new Tile(2837, 3499, 0), 
			new Tile(2839, 3494, 0), new Tile(2844, 3493, 0), new Tile(2844, 3498, 0), 
			new Tile(2847, 3502, 0), new Tile(2850, 3503, 0) };
	
	public final Tile[] pathToCatherbyStore = new Tile[] { 
			new Tile(2830, 3452, 0), new Tile(2828, 3447, 0), new Tile(2826, 3442, 0), 
			new Tile(2824, 3437, 0), new Tile(2819, 3435, 0), new Tile(2814, 3435, 0), 
			new Tile(2809, 3435, 0), new Tile(2805, 3432, 0), new Tile(2804,3430, 0) };
	
	public final Tile[] pathToSeers = new Tile[] { 
			new Tile(2831, 3450, 0), new Tile(2827, 3447, 0), new Tile(2824, 3443, 0), 
			new Tile(2822, 3438, 0), new Tile(2817, 3436, 0), new Tile(2812, 3435, 0), 
			new Tile(2807, 3434, 0), new Tile(2802, 3434, 0), new Tile(2797, 3432, 0), 
			new Tile(2792, 3432, 0), new Tile(2788, 3435, 0), new Tile(2786, 3440, 0), 
			new Tile(2783, 3444, 0), new Tile(2779, 3447, 0), new Tile(2775, 3450, 0), 
			new Tile(2771, 3453, 0), new Tile(2768, 3457, 0), new Tile(2765, 3461, 0), 
			new Tile(2762, 3465, 0), new Tile(2761, 3470, 0), new Tile(2758, 3475, 0), 
			new Tile(2753, 3477, 0), new Tile(2748, 3478, 0), new Tile(2743, 3479, 0), 
			new Tile(2739, 3482, 0), new Tile(2734, 3484, 0), new Tile(2729, 3484, 0), 
			new Tile(2724, 3484, 0), new Tile(2719, 3484, 0), new Tile(2714, 3483, 0), 
			new Tile(2709, 3483, 0), new Tile(2704, 3480, 0), new Tile(2700, 3478, 0) };
	
	 public final Tile[] pathToGoblinCaveEntrance = new Tile[] { 
			new Tile(2633, 3347, 0), new Tile(2636, 3351, 0), new Tile(2636, 3356, 0), 
			new Tile(2636, 3361, 0), new Tile(2635, 3366, 0), new Tile(2634, 3371, 0), 
			new Tile(2633, 3376, 0), new Tile(2630, 3380, 0), new Tile(2627, 3384, 0), 
			new Tile(2627, 3389, 0), new Tile(2628, 3394, 0) };
	 
	 public final Tile[] pathToWizardInAdrougne = new Tile[] { 
			    new Tile(2633, 3349, 0), new Tile(2633, 3344, 0), new Tile(2635, 3339, 0), 
				new Tile(2638, 3335, 0), new Tile(2642, 3332, 0), new Tile(2645, 3328, 0), 
				new Tile(2647, 3323, 0), new Tile(2648, 3318, 0), new Tile(2652, 3315, 0), 
				new Tile(2657, 3315, 0), new Tile(2662, 3316, 0), new Tile(2667, 3314, 0), 
				new Tile(2672, 3312, 0), new Tile(2677, 3312, 0), new Tile(2679, 3317, 0), 
				new Tile(2677, 3322, 0), new Tile(2677, 3325, 0) };
	 
	 public final Tile[] pathToPortKhazard = new Tile[] { 
			    new Tile(2634, 3351, 0), new Tile(2634, 3346, 0), new Tile(2634, 3341, 0), 
				new Tile(2636, 3336, 0), new Tile(2641, 3334, 0), new Tile(2645, 3331, 0), 
				new Tile(2648, 3327, 0), new Tile(2649, 3322, 0), new Tile(2651, 3317, 0), 
				new Tile(2653, 3312, 0), new Tile(2653, 3307, 0), new Tile(2648, 3306, 0), 
				new Tile(2643, 3306, 0), new Tile(2638, 3304, 0), new Tile(2636, 3299, 0), 
				new Tile(2631, 3298, 0), new Tile(2626, 3296, 0), new Tile(2621, 3296, 0), 
				new Tile(2616, 3295, 0), new Tile(2611, 3295, 0), new Tile(2607, 3292, 0), 
				new Tile(2606, 3287, 0), new Tile(2608, 3282, 0), new Tile(2606, 3277, 0), 
				new Tile(2601, 3274, 0), new Tile(2598, 3270, 0), new Tile(2598, 3265, 0), 
				new Tile(2596, 3260, 0), new Tile(2594, 3255, 0), new Tile(2592, 3250, 0), 
				new Tile(2595, 3246, 0), new Tile(2598, 3242, 0), new Tile(2601, 3238, 0), 
				new Tile(2603, 3233, 0), new Tile(2604, 3228, 0), new Tile(2606, 3223, 0), 
				new Tile(2610, 3220, 0), new Tile(2615, 3219, 0), new Tile(2620, 3218, 0), 
				new Tile(2622, 3213, 0), new Tile(2624, 3208, 0), new Tile(2623, 3203, 0), 
				new Tile(2622, 3198, 0), new Tile(2623, 3193, 0), new Tile(2624, 3188, 0), 
				new Tile(2624, 3183, 0), new Tile(2624, 3178, 0), new Tile(2625, 3173, 0), 
				new Tile(2628, 3169, 0), new Tile(2633, 3167, 0), new Tile(2638, 3166, 0), 
				new Tile(2642, 3163, 0), new Tile(2645, 3159, 0), new Tile(2650, 3157, 0), 
				new Tile(2654, 3155, 0), new Tile(2658, 3152, 0), new Tile(2662, 3149, 0), 
				new Tile(2667, 3149, 0), new Tile(2672, 3151, 0), new Tile(2676, 3151, 0) };
	 
	public final Tile[] pathToOgre = new Tile[] {
			new Tile(2528, 3094, 0), new Tile(2528, 3089, 0), new Tile(2528, 3084, 0), 
			new Tile(2527, 3079, 0), new Tile(2525, 3074, 0), new Tile(2522, 3070, 0), 
			new Tile(2517, 3069, 0), new Tile(2512, 3069, 0), new Tile(2507, 3070, 0), 
			new Tile(2502, 3071, 0), new Tile(2497, 3072, 0), new Tile(2492, 3072, 0), 
			new Tile(2487, 3072, 0), new Tile(2482, 3072, 0), new Tile(2477, 3071, 0), 
			new Tile(2472, 3071, 0), new Tile(2467, 3070, 0), new Tile(2462, 3069, 0), 
			new Tile(2457, 3069, 0), new Tile(2452, 3067, 0), new Tile(2447, 3065, 0), 
			new Tile(2443, 3062, 0), new Tile(2439, 3059, 0), new Tile(2437, 3054, 0), 
			new Tile(2437, 3049, 0), new Tile(2438, 3044, 0), new Tile(2441, 3040, 0), 
			new Tile(2445, 3037, 0), new Tile(2447, 3032, 0), new Tile(2451, 3029, 0), 
			new Tile(2455, 3026, 0), new Tile(2459, 3023, 0), new Tile(2464, 3021, 0), 
			new Tile(2470, 3020, 0), new Tile(2474, 3014, 0), new Tile(2480, 3006, 0), 
			new Tile(2484, 3003, 0), new Tile(2485, 2998, 0), new Tile(2482, 2992, 0), 
			new Tile(2481, 2986, 0), new Tile(2483, 2981, 0), new Tile(2488, 2980, 0), 
			new Tile(2493, 2978, 0), new Tile(2497, 2975, 0), new Tile(2501, 2972, 0), 
			new Tile(2506, 2970, 0), new Tile(2511, 2969, 0), new Tile(2516, 2968, 0), 
			new Tile(2521, 2968, 0), new Tile(2526, 2968, 0), new Tile(2531, 2968, 0), 
			new Tile(2536, 2968, 0), new Tile(2541, 2970, 0), new Tile(2545, 2973, 0), 
			new Tile(2550, 2974, 0), new Tile(2555, 2974, 0), new Tile(2560, 2974, 0), 
			new Tile(2565, 2974, 0), new Tile(2570, 2975, 0), new Tile(2575, 2976, 0), 
			new Tile(2581, 2978, 0), new Tile(2586, 2981, 0), new Tile(2590, 2985, 0), 
			new Tile(2594, 2988, 0), new Tile(2599, 2988, 0), new Tile(2604, 2988, 0), 
			new Tile(2609, 2987, 0), new Tile(2614, 2984, 0), new Tile(2619, 2982, 0), 
			new Tile(2624, 2982, 0), new Tile(2629, 2981, 0), new Tile(2630, 2980, 0) };
	
	public final Tile[] pathToLastGnome = new Tile[] { 
			new Tile(2629, 2980, 0), new Tile(2625, 2977, 0), new Tile(2620, 2977, 0), 
			new Tile(2616, 2980, 0), new Tile(2611, 2982, 0), new Tile(2606, 2984, 0), 
			new Tile(2601, 2984, 0), new Tile(2596, 2981, 0), new Tile(2591, 2979, 0), 
			new Tile(2586, 2977, 0), new Tile(2581, 2975, 0), new Tile(2576, 2973, 0), 
			new Tile(2572, 2970, 0), new Tile(2567, 2969, 0), new Tile(2562, 2968, 0), 
			new Tile(2557, 2967, 0), new Tile(2552, 2967, 0), new Tile(2547, 2967, 0), 
			new Tile(2542, 2969, 0), new Tile(2542, 2971, 0) };
	
	public final Tile[] pathToCages = new Tile[] { 
			new Tile(2632, 3349, 0), new Tile(2634, 3344, 0), new Tile(2635, 3339, 0), 
			new Tile(2638, 3335, 0), new Tile(2642, 3332, 0), new Tile(2647, 3330, 0), 
			new Tile(2649, 3325, 0), new Tile(2648, 3320, 0), new Tile(2643, 3318, 0), 
			new Tile(2642, 3313, 0), new Tile(2641, 3308, 0), new Tile(2638, 3303, 0), 
			new Tile(2634, 3300, 0), new Tile(2629, 3299, 0), new Tile(2624, 3297, 0), 
			new Tile(2619, 3297, 0), new Tile(2614, 3297, 0), new Tile(2609, 3296, 0), 
			new Tile(2606, 3300, 0), new Tile(2606, 3305, 0), new Tile(2606, 3310, 0), 
			new Tile(2606, 3315, 0), new Tile(2606, 3319, 0), new Tile(2609, 3323, 0), 
			new Tile(2615, 3322, 0), new Tile(2618, 3323, 0), new Tile(2619, 3324, 0) };
	
	public Tile initTile;
	public boolean loc1 = false;
	public boolean hasGuthixTea = false;
	public boolean hasHerbs = false;
	public boolean hasCages = false;
	public boolean crushedGem = false;
	public boolean repairedWv = false;
	public boolean madePot = false;
	public Timer lightTimer = new Timer(0);
	 public Timer speakTimer = new Timer(0); 
	public int itemsArray[] = {0,0,0,0,0,0,0,0,0};//contains the states of items needing to be purchased.
	public int itemDAmount[] = {3,2,1,1,1,1,1,1,1,1};
	public int itemDID[] = {2354,250,251,255,1980,4456,2349,2351,1931};//contains the ids of the items needing to be purchased.
	public int itemDPrice[] = {1500,1500,1500,1500,1500,2500,1500,1500,1500,1200};//contains specific prices to use upon purchasing specific items.
	public String itemDString[] = {"Steel bar","Clean guam","Clean marrentill","Clean harralander","Empty cup",
			"Bowl of hot water","Bronze bar","Iron bar","Empty pot"};//contains the names of the items needing to be purchased.
	
	public int [] bankItems = {2353,2353,2353,249,249,251,255,1980,4456,2349,235,1931};
	public boolean activate() {
		return DeltaQuester.scriptToStart==29;
	}


	public void execute() {
		DeltaQuester.numSteps = 45;
		Method.resetTeleporting();
		Method.foodSupport();
		if(!Method.teleporting&&!Method.isInCombat() && Inventory.isFull()){//An attempt to make room for new items in the quest
			if(Inventory.getItem(DeltaQuester.FOOD_ID)!=null){
				Method.interactInventory(DeltaQuester.FOOD_ID, "Eat", "Eating food to make room for items");
			}
		}
		if (DeltaQuester.GEFeature && (Settings.get(2671)&0x1FF) !=280) {
			Method.useGE(itemDString, itemDID, itemDPrice, itemDAmount);
		}else if(Method.useBank){
			Method.useBank(bankItems, 10, 90, 90);
		}else
		if((Settings.get(2671)&0x1FF) ==280 ||(Settings.get(2671)&0x1FF) ==285){
			DeltaQuester.progress =45;
			Quests.state("The One Small Favour quest has been completed.");
			Task.sleep(2000);
			DeltaQuester.e = true;
		}else 
		if((Settings.get(2671)&0x1FF) ==275){
			DeltaQuester.progress =44;
			cs1();//Finish the quest
		}else 
		if((Settings.get(2671)&0x1FF) ==270){
			DeltaQuester.progress =43;
			cs2();//Give the forester the hatchet and receive the mohoganny logs/
		}else 
		if((Settings.get(2671)&0x1FF) ==265){
			DeltaQuester.progress =42;
			cs3();//Speak to brain in port sarim, and receive the hatchet trimmed
		}else 
		if((Settings.get(2671)&0x1FF) ==260){
			DeltaQuester.progress =41;
			cs4();//Speak to aggie the witch one last time.
		}else 
		if((Settings.get(2671)&0xFF) ==255){
			DeltaQuester.progress =40;
			cs5();//Speak to the people in the H.A.M head questers again(last time)
		}else 
		if((Settings.get(2671)&0xFF) ==250){
			DeltaQuester.progress =39;
			cs7();//Givew the cages to Seth thge farmer
		}else 
		if((Settings.get(2671)&0xFF) ==245){
			DeltaQuester.progress =38;
			cs24();//Make he pigeon cages into chicken, at the smith(also gathers cages)
		}else 
		if((Settings.get(2671)&0xFF) ==240){
			DeltaQuester.progress =37;
			cs8();//Speak to the smith man again(give him the breathing salts )
		}else 
		if((Settings.get(2671)&0xFF) ==235){
			DeltaQuester.progress =36;
			if(madePot){
				cs9();//Speak to the potion man in Varrock and give him the pot
			}else if(!Method.teleporting && Inventory.getItem(4436)!=null){
				Vars.DYNAMICV = false;
				Vars.DYNAMICV2 = false;
				madePot = true;
			}else makePot();
		}else 
		if((Settings.get(2671)&0xFF) ==230){
			DeltaQuester.progress =35;
			cs10();//Speak to the potter again in Barb village
		}else 
		if((Settings.get(2671)&0xFF) ==205||(Settings.get(2671)&0xFF) ==210||(Settings.get(2671)&0xFF) ==215||(Settings.get(2671)&0xFF) ==220||(Settings.get(2671)&0xFF) ==225){
			DeltaQuester.progress =34;
			cs11();//Speak to the gangster dwarf in the mines and defeat his men
		}else 
		if((Settings.get(2671)&0xFF) ==200){
			DeltaQuester.progress =33;
			cs12();//Speak to Sanfew again.
		}else 
		if((Settings.get(2671)&0xFF) ==195){
			DeltaQuester.progress =32;
			cs13();//Speak to the gnome in white wolf mountain again(last time)
		}else 
		if((Settings.get(2671)&0xFF) ==190){
			DeltaQuester.progress =31;
			cs14();//Speak to the man that owns the general store in CAtheryby again
		}else 
		if((Settings.get(2671)&0xFF) ==185){
			DeltaQuester.progress =30;
			cs15();//
		}else 
		if((Settings.get(2671)&0xFF) ==180){
			DeltaQuester.progress =29;
			cs23();//Repair the weathervain and assemble pieces again.
		}else 
		if((Settings.get(2671)&0xFF) ==175||(Settings.get(2671)&0xFF) ==176||(Settings.get(2671)&0xFF) ==177){
			DeltaQuester.progress =28;
			cs22();//Gather the peices of the weathervain
		}else 
		if((Settings.get(2671)&0xFF) ==160||(Settings.get(2671)&0xFF) ==165||(Settings.get(2671)&0xFF) ==170){
			DeltaQuester.progress =27;
			cs15();//Get the weather man to explain what his problem is.
		}else 
		if((Settings.get(2671)&0xFF) ==145||(Settings.get(2671)&0xFF) ==150||(Settings.get(2671)&0xFF) ==152||(Settings.get(2671)&0xFF) ==155){
			DeltaQuester.progress =26;
			cs16();//Free and speak to the warrior in the wall(also fight the rock monster.)
		}else 
		if((Settings.get(2671)&0xFF) ==140){
			DeltaQuester.progress =25;
			cs17();//Speak to the wizard again. Give him the iron oxide so we may receive the spells
		}else 
		if((Settings.get(2671)&0xFF) ==135){
			DeltaQuester.progress =24;
			cs18();//Speak to the port man and receive the Iron oxide.
		}else 
		if((Settings.get(2671)&0xFF) ==130){
			DeltaQuester.progress =23;
			cs19();//Speak to the Ogre again
		}else 
		if((Settings.get(2671)&0x7F) ==125){
			DeltaQuester.progress =22;
			cs20();//Speak to the gnome again after the puzzle
		}else 
		if((Settings.get(2671)&0x7F) ==120){
			DeltaQuester.progress =21;
			cs21();//Fix the gnomes landing lights
		}else 
		if((Settings.get(2671)&0x7F) ==115){
			DeltaQuester.progress =20;
			cs20();//Speak to the last gnome about the noise
		}else 
		if((Settings.get(2671)&0x7F) ==110){
			DeltaQuester.progress =19;
			cs19();//Speak to the ogre
		}else 
		if((Settings.get(2671)&0x7F) ==105){
			DeltaQuester.progress =18;
			cs18();//Speak to the merchant far down in port khazard(mattress guy)
		}else 
		if((Settings.get(2671)&0x7F) ==100){
			DeltaQuester.progress =17;
			cs17();//Speak to the wizard about the girl in the wall.
		}else 
		if((Settings.get(2671)&0x7F) ==95){
			DeltaQuester.progress = 16;
			cs16();//Speak to the stone statue in the goblin cave.
		}else 
		if((Settings.get(2671)&0x7F) ==90){
			DeltaQuester.progress = 15;
			cs15();//Speak to the weather man in Seers
		}else 
		if((Settings.get(2671)&0x7F) == 88){
			DeltaQuester.progress = 14;
			cs14();//Speak to man in Catherby general store
		}else 
		if((Settings.get(2671)&0x7F) == 80||(Settings.get(2671)&0x7F) == 82||(Settings.get(2671)&0x7F) == 83||(Settings.get(2671)&0x7F) == 84||(Settings.get(2671)&0x7F) == 81||(Settings.get(2671)&0x7F) == 86){//86 if after giving tea
			DeltaQuester.progress = 13;
			cs13();//Speak to the gnome in white wolf mountain(also take tea out of bank(if in bank))
		}else 
		if((Settings.get(2671)&0x7F) == 75){
			DeltaQuester.progress = 12;
			cs12();//Speak to Sanfew in taverly
		}else 
		if((Settings.get(2671)&0x7F) == 70){
			DeltaQuester.progress = 11;
			cs11();//Speak to the gangster dwarf in the mines
		}else 
		if((Settings.get(2671)&0x7F) == 65){
			DeltaQuester.progress = 10;
			cs10();//Speak to potter in Barbarian village
		}else 
		if((Settings.get(2671)&0x3F) == 60 || (Settings.get(2671)&0x3F) == 62||(Settings.get(2671)&0x3F) == 63){
			DeltaQuester.progress = 9;
			cs9();//Speak to the potion man in Varrok(he smashes...salt)
		}else 
		if((Settings.get(2671)&0x3F) == 55){
			DeltaQuester.progress = 8;
			cs8();//Speak to the smith in Varrok
		}else 
		if((Settings.get(2671)&0x3F) == 50){
			DeltaQuester.progress = 7;
			cs7();//Speak to Seth the farmer
		}else 
		if((Settings.get(2671)&0x3F) == 45){
			DeltaQuester.progress = 6;
			cs6();//Speak to Fred the farmer
		}else 
		if((Settings.get(2671)&0x1F) == 25){
			DeltaQuester.progress = 5;
			cs5();//Speak to Johan in the H.A.M headquarters.(get another favor..)
		}else 
		if((Settings.get(2671)&0x1F) == 20){
			DeltaQuester.progress = 4;
			cs4();//Speak to Aggie and receive another favor
		}else 
		if((Settings.get(2671)&0xF) == 10){
			DeltaQuester.progress = 3;
			cs3();//Speak to Brian in Port Sarim about the hatchet(and get another favour)
		}else 
		if((Settings.get(2671)&0x7) == 5){
			DeltaQuester.progress = 2;
			cs2();//Speak to jungle forester and receive his hatchet
		}else 
		if((Settings.get(2671)&0x1) ==0){
			DeltaQuester.progress = 1;
			cs1();//Start the quest
		}
		
	}

	private void cs24() {
		final String opt[] = {"I have the"};
		Method.skipPics();
		if(hasCages){
			if(new Tile(3232,3440,0).distanceTo()<10){
				if(!Method.findOption(opt)){
					if(!Method.isChatting("Horvik")){
							Method.speakTo(549, "Horvik");
					}
				}
			}else cs8();
			
		}else gatherCages();
	}


	private void makePot() {
		if(!Method.teleporting &&Inventory.getItem(4440)!=null){
			if(Inventory.getSelectedItem()!=null){
				Method.interactInventory(1931, "Use", "Pot");
			}else Method.interactInventory(4440, "Use", "Lid");
		}else
		if(new Tile(3085,3409,0).distanceTo()<7){
			if(Inventory.getItem(1761)!=null){//soft clay
				if(Players.getLocal().isIdle()){
			if(Widgets.get(1370).validate()){
				if(Widgets.get(1370,56).getText().contains("Unfired pot lid")){
					Widgets.get(1370,12).click(true);
				}else Widgets.get(1371,44).getChild(18).click(true);
			}else
				Method.useItemOn(1761,2642,"Wheel");
				}
			}else if(Inventory.getItem(4438)!=null){//unfired pot lid
				if(Players.getLocal().isIdle()){
			if(Widgets.get(1370).validate()){
				if(Widgets.get(1370,56).getText().contains("Pot lid")){
					Widgets.get(1370,12).click(true);
				}else Widgets.get(1371,44).getChild(22).click(true);
			}else
				Method.useItemOn(4438,11602,"Ovan");
				}
			}
		}else cs10();
		
	}


	private void cs23() {
		if(repairedWv){
			cs22();//Head to the roof and repair the weathervain
		}else
		if (new Tile(2709, 3487, 0).canReach()) {
			if (new Tile(2711, 3494, 0).distanceTo() < 6) {
				Vars.DYNAMICV = false;
				Vars.DYNAMICV2 = false;
				while(Widgets.get(1189).validate() || Widgets.get(1186).validate()){
					ChatOptions.getContinueOption().select(true);
				}
				if(Inventory.getItem(4431)!=null){
					useItemOnAnvil(4431);
				}else if(Inventory.getItem(4429)!=null){
					useItemOnAnvil(4429);
				}if(Inventory.getItem(4433)!=null){
					useItemOnAnvil(4433);
				}else repairedWv = true;
				
			} else new Tile(2711, 3494, 0).clickOnMap();
		} else if (new Tile(2709, 3487, 0).distanceTo() < 20 && new Tile(2709, 3487, 0).canReach()) {
			if (new Tile(2709, 3486, 0).distanceTo() < 8) {
				Method.interactO(2416, "Open", "Door");
			} else Method.findPath(new Tile(2709, 3487, 0));
		} else cs15();//Walk to Seers village
		
	}

	private void useItemOnVain(int i) {
		if(Inventory.getSelectedItem()!=null){
			Method.interactO(5811, "Use", "Weathervain");
		}else Inventory.selectItem(i);
		
	}
	
	private void useItemOnAnvil(int i) {
		if(Inventory.getSelectedItem()!=null){
			Method.interactO(2783, "Use", "Anvil");
		}else Inventory.selectItem(i);
		
	}


	private void cs22() {
		final String opt[] = {"Yes, hit them"};
		while(Widgets.get(1186).validate()||Widgets.get(1189).validate()){
			Method.state("Selecting continue");
			ChatOptions.getContinueOption().select(true);
		}
		if(new Tile(2714,3472,3).canReach()){
			if(new Tile(2702,3475,3).distanceTo()<7){
				Vars.DYNAMICV = false;
				Vars.DYNAMICV2 = false;
				Vars.DYNAMICV3 = false;
				if(repairedWv){
					if(Inventory.getItem(4432)!=null){
						useItemOnVain(4432);
					}else if(Inventory.getItem(4430)!=null){
						useItemOnVain(4430);
					}else if(Inventory.getItem(4434)!=null){
						useItemOnVain(4434);
					}
				}else{
				if(!Method.findOption(opt)){
					if(!speakTimer.isRunning()){
				Method.interactO(5811, "Search", "Weathervane");
					speakTimer = new Timer(4000);
					}
				}
				}
			}else {Method.state("Walking to Weathervane");
				new Tile(2702,3475,3).clickOnMap();
			}
		}else
		if(new Tile(2714,3472,1).canReach()){
			if(SceneEntities.getNearest(26118)!=null &&SceneEntities.getNearest(26118).getLocation().distanceTo()<4){
				Method.interactO(26118, "Climb","Ladder");//This is the final ladder to the roof
				}else SceneEntities.getNearest(26118).getLocation().clickOnMap();
		}else
		if(new Tile(2709,3472,1).canReach()){
			if(SceneEntities.getNearest(25819)!=null &&SceneEntities.getNearest(25819).getLocation().distanceTo()<4){
				Method.interactO(25819, "Open","Door");
				}else SceneEntities.getNearest(25819).getLocation().clickOnMap();
		}else
		if(new Tile(2699,3475,1).canReach()){
			if(SceneEntities.getNearest(25819)!=null &&SceneEntities.getNearest(25819).getLocation().distanceTo()<4){
			Method.interactO(25819, "Open","Door");
			}else SceneEntities.getNearest(25819).getLocation().clickOnMap();
		}else if(new Tile(2701,3474,0).canReach() && new Tile(2701,3474,0).distanceTo()<8){
			Method.interactO(25941, "Climb", "Ladder");
		}else cs15();//Walk to Seers village and enter the house.
		
	}


	private void gatherCages() {
		if(!Method.teleporting && (Inventory.getItem(424)!=null &&Inventory.getCount(424) >=5)){
			hasCages = true;
		}else
		if(new Tile(2619, 3324, 0).distanceTo()<4){
			if(Players.getLocal().isIdle())
			Vars.DYNAMICV = false;
			if(GroundItems.getNearest(424)!=null){
				GroundItems.getNearest(424).interact("Take");
				Task.sleep(1500);
			}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToCages, "Walking to gather pigeon cages", false);
		}else if(Vars.ARDOUGNELODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(Vars.ARDOUGNETELEPORT);
		
	}


	private void cs21() {
		if(new Tile(2549,2971,0).distanceTo()<14){
			
		 if((Settings.get(2672)>>16&0x1) !=1){//must fix #1
			 fixLight(5823, new Tile(2545,2970,0));
		 }else
		 if((Settings.get(2672)>>15&1) !=1){//must fix #2
			 fixLight(5821, new Tile(2548,2970,0));
		 }else if((Settings.get(2672)>>14&1) !=1){//must fix #3
			 fixLight(5822, new Tile(2551,2970,0));
		 }else if((Settings.get(2672)>>13&1) !=1){//must fix #4
			 fixLight(5820, new Tile(2553,2970,0));
		 }else if((Settings.get(2672)>>9&1) !=1){//must fix #5
			 fixLight(5820, new Tile(2554,2973,0));
		 }else if((Settings.get(2672)>>10&1) !=1){//must fix #6
			 fixLight(5822, new Tile(2551,2973,0));
		 }else if((Settings.get(2672)>>11&1) !=1){//must fix #7
			 fixLight(5821, new Tile(2548,2973,0));
		 }else if((Settings.get(2672)>>12&1) !=1){//must fix #8
			 fixLight(5823, new Tile(2545,2973,0));
		 }
		}else if(Vars.DYNAMICV){
			Method.walking(pathToOgre, "Walking to the Gnome area", false);
		}else if(Vars.YANILLELODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(Vars.YANILLETELEPORT);
		
	}

;
	private void fixLight(int i, Tile tile) {
		final int uncutgems[]  ={1623,1625,1629,1627};
		final int cutGem[]  = {1607,1609,1613,1611};
		final String opt[] = {"Ok, here's 500","I need a new"};
		
		if(Inventory.getItem(1633)!=null){
			crushedGem = true;
		}
		while(crushedGem){
			for(int f: uncutgems){
				if(Inventory.getItem(f)!=null){
					if(Inventory.getItem(1633)!=null){
						Method.interactInventory(1633, "Drop", "Crushed gem");
					}else
					crushedGem = false;
				}
				if(!Method.findOption(opt)){
					if(!Method.isChatting("Gnome")){
						if(NPCs.getNearest(1800)!=null && NPCs.getNearest(1800).getLocation().distanceTo()<6){
							if(!speakTimer.isRunning()){
							Method.speakTo(1800, "Gnome");
							speakTimer = new Timer(8500);
							}
						}else new Tile(2544,2972,0).clickOnMap();
					}
				}
			}
			
		}
	 if(tile.distanceTo()<2){
		
			for (int c = 0 ; c < cutGem.length;) {
				
				 for(int g = 0; g < 4;){
					 if(Widgets.get(1370).validate()){
						 if(Inventory.getItem(uncutgems[g])!=null){
						 Widgets.get(1370,12).click(true);
						 }else g++;
					 
					 }else if(Inventory.getItem(uncutgems[g])!=null){
						 if(!lightTimer.isRunning()){
							 Method.interactInventory(uncutgems[g], "Craft", "Gem");
						 lightTimer = new Timer(1200);
						 }
					 }else g++;
				 }
				 if(!Widgets.get(1370).validate()){
				if (Inventory.getItem(cutGem[c]) != null) {
					if (Inventory.getSelectedItem() != null) {
						Method.interactO(i, "Use", "Light");
					} else Method.interactInventory(cutGem[c], "Use", "Gem");
				} else {
					if(Inventory.getItem(1633)!=null)
						break;
					
						if(!lightTimer.isRunning()){
					Method.interactO(i, "Search", "Light");
					c++;
					lightTimer = new Timer(2000);
						}
					}
				}else Players.getLocal().getLocation().clickOnMap();
			}
	 }else tile.clickOnMap();
		
	}


	private void cs20() {
		final String opt[] = {"I've fixed","Yes, I'll take a look","Rantz said I should"};
		if(new Tile(2540,2971,0).distanceTo()<8){
			if (!Method.findOption(opt)) {
				Vars.DYNAMICV = false;
				if (!Method.isChatting("Gnome")) {
					if (NPCs.getNearest(1800) != null) {
						if (NPCs.getNearest(1800).getLocation().distanceTo() < 6) {
							Method.speakTo(1800, "Gnome");
						} else
							NPCs.getNearest(1800).getLocation().clickOnMap();

					}
				}
			}
		}else if(Vars.DYNAMICV2){
			Method.walking(pathToLastGnome, "Walking to the gnome", false);
		}else if(Vars.DYNAMICV){
			Method.walking(pathToOgre, "Walking to the Ogre area", false);
		}else if(Vars.YANILLELODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(Vars.YANILLETELEPORT);
		
	}


	private void cs19() {
		final String opt[] ={"Ok, I've helped","Ok, I'll see what I","Can you fill","I need to talk"};
		while(Widgets.get(1186).validate()||Widgets.get(1189).validate()){
			ChatOptions.getContinueOption().select(true);
		}
		if(new Tile(2625, 2981, 0).distanceTo()<10){
			Vars.DYNAMICV2 = true;
			Vars.DYNAMICV = false;
			if(!Method.findOption(opt)){
				if(!Method.isChatting("Rantz")){
					if(NPCs.getNearest(8659)!=null){
						if(NPCs.getNearest(8659).getLocation().distanceTo()<6){
							Method.speakTo(8659, "Weather man");
						}else NPCs.getNearest(8659).getLocation().clickOnMap();
						
						}
				}
			}
		}else if(Vars.DYNAMICV2){
			Method.walking(pathToLastGnome, "Walking back to the Ogre", true);
		}else if(new Tile(2547,2971,0).distanceTo()<8){
			Vars.DYNAMICV2 = true;
		}else if(Vars.DYNAMICV){
			Method.walking(pathToOgre, "Walking to the Ogre", false);
		}else if(Vars.YANILLELODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(Vars.YANILLETELEPORT);
		
	}


	private void cs18() {
		final String opt[] = {"I have the ","Ok, I'll do it!","Ask about iron"};
		
		while(Widgets.get(1186).validate()||Widgets.get(1189).validate()){
			ChatOptions.getContinueOption().select(true);
		}
		if(new Tile(2676, 3151, 0).distanceTo()<8){
			Vars.DYNAMICV = false;
			if(!Method.findOption(opt)){
				if(!Method.isChatting("Merchant")){
					Method.speakTo(1799, "Merchant");
				}
			}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToPortKhazard, "Walking to Port Khazard", false);
		}else if(Vars.ARDOUGNELODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(Vars.ARDOUGNETELEPORT);
		
	}


	private void cs17() {
		final String opt[]  ={"I have that iron","Oh! Ok, one more","I need to talk to you"};
		while(Widgets.get(1189).validate()){
			ChatOptions.getContinueOption().select(true);
		}
		if(new Tile(2677, 3325, 0).distanceTo()<8){
			if(new Tile(2681,3325,0).canReach()){
				Vars.DYNAMICV = false;
				initTile = null;
				if(!Method.findOption(opt)){
					if(!Method.isChatting("Wizard")){
						Method.speakTo(844, "Wizard");
					}
				}
			}else Method.interactO(34819, "Open", "Door");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToWizardInAdrougne, "Walking to the wizard", false);
		}else if(Vars.ARDOUGNELODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(Vars.ARDOUGNETELEPORT);
		
	}


	private void cs16() {
		int wall[] = {2180,21801};
		if(initTile!=null){
			//Method.state("X: " + (initTile.x() - Players.getLocal().getLocation().x())+" Y: " +
			//(initTile.getY() - Players.getLocal().getLocation().getY()));
			if(new Tile(initTile.x(), initTile.getY()+38,0).distanceTo()<8){
				if((Settings.get(2671)&0xFF) ==155){
					if(NPCs.getNearest(1801)==null){
						if(Players.getLocal().isIdle())
						Method.interactInventory(4428, "Read", "Scroll");
					}else
					if(!Method.isChatting("Warrior")){
						Method.speakTo(1801,"Warrior");
					}
				}else
				if((Settings.get(2671)&0xFF) ==145||(Settings.get(2671)&0xFF) ==150||(Settings.get(2671)&0xFF) ==152){//Our second visit.
					if(Method.isInCombat()){
						Method.basicFightNPC(1802);
					}else if(Players.getLocal().isIdle())
						Method.interactInventory(4428, "Read", "Scroll");
				}else{//..First visit
				Vars.DYNAMICV = false;
				for(int h: wall)
				Method.interactO(h, "Search", "Wall");
				}
			}else new Tile(initTile.x(), initTile.getY()+38,0).clickOnMap();
		}else if(SceneEntities.getNearest(31130)!=null){
			initTile = Players.getLocal().getLocation();
		}else
		if(new Tile(2628, 3394, 0).distanceTo()<8){
			Method.interactO(2, "Enter", "Cave");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToGoblinCaveEntrance, "Walking to the goblin cave", false);
		}else if(Vars.ARDOUGNELODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(Vars.ARDOUGNETELEPORT);
		
	}


	private void cs15() {
		final String opt []  ={"I've fixed the weather","What do you mean","Which special Seers","I'll run","Why can't","I've released Petra","Yes, Ok, I'll","What can I","Who is this girl that","Hi, can you give me"};
		
		while(Widgets.get(1189).validate()){
			ChatOptions.getContinueOption().select(true);
		}
		if(new Tile(2701, 3477, 0) .distanceTo()<8 &&new Tile(2700, 3478, 0).canReach()){
			if(new Tile(2702,3474,0).canReach()){
				Vars.DYNAMICV = false;
				initTile = null;
				if(!Method.findOption(opt)){
					if(!Method.isChatting("Weather man")){
						if(NPCs.getNearest(1798)!=null){
						if(NPCs.getNearest(1798).getLocation().distanceTo()<6){
							Method.speakTo(1798, "Weather man");
						}else NPCs.getNearest(1798).getLocation().clickOnMap();
						
						}
					}
				}
			}else Method.interactO(25819, "Open", "Door");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToSeers, "Walking to Seers Village", false);
		}else if(Vars.CATHERBYLODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(Vars.CATHERBYTELEPORT);
		
	}


	private void cs14() {
		final String opt []  ={"I have the weather","Yes, Ok, I'll","I need to talk"};
		while(Widgets.get(1189).validate()){
			ChatOptions.getContinueOption().select(true);
		}
		if(new Tile(2804,3430, 0).distanceTo()<5){
			Vars.DYNAMICV = false;
			if(!Method.findOption(opt)){
				if(!Method.isChatting("Arhein")){
					Method.speakTo(563, "Arhein");
				}
			}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToCatherbyStore, "Walking to general store", false);
		}else if(Vars.CATHERBYLODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(Vars.CATHERBYTELEPORT);
		
	}


	private void cs13() {
		final String opt []  ={"Hey there,","Ok, I'll go and get","I have a special","How was that tea"};
		while(Widgets.get(1189).validate()){
			ChatOptions.getContinueOption().select(true);
		}
		if(hasGuthixTea || (Settings.get(2671)&0xFF) >= 86){
			if(new Tile(2845,3501, 0).distanceTo()<5){//3810
				Vars.DYNAMICV  =false;
				if(Method.isInCombat()){
					Method.basicFightNPC(6046);
				}else
				if(!Method.findOption(opt)){
					if(!Method.isChatting("Gnome")){
						Method.speakTo(3810, "Gnome");
					}
				}
			}else if(Vars.DYNAMICV){
				Method.walking(pathToGnome, "Walking to the gnome", false);
			}else if(Vars.BURTHORPELODE.distanceTo()<10){
				Vars.DYNAMICV = true;
			}else Method.teleportTo(Vars.BURTHORPETELEPORT);
		}else if(!Method.teleporting && !Method.isInCombat() &&Inventory.getItem(4419)!=null){//checks if you have the tea in inventory
			hasGuthixTea = true;
		}else makeTea();
	}


	private void makeTea() {
	  if(!Method.teleporting){
		  if(Inventory.getItem(1980)!=null){//Empty cup
			  if(Inventory.getSelectedItem()!=null){
				  Method.interactInventory(1980, "Use", "Empty cup");
			  }else Inventory.selectItem(4456);
		  }else  if(Inventory.getItem(4460)!=null){//cup of hot water
			  if(Inventory.getSelectedItem()!=null){
				  Method.interactInventory(4460, "Use", "Cup of hot water");
			  }else Inventory.selectItem(249);
		  }else  if(Inventory.getItem(4466)!=null){//
			  if(Inventory.getSelectedItem()!=null){
				  Method.interactInventory(4466, "Use", "Cup of unfinished tea");
			  }else Inventory.selectItem(249);
		  }else  if(Inventory.getItem(4474)!=null){//
			  if(Inventory.getSelectedItem()!=null){
				  Method.interactInventory(4474, "Use", "Cup of unfinished tea");
			  }else Inventory.selectItem(251);
		  }else  if(Inventory.getItem(4480)!=null){//
			  if(Inventory.getSelectedItem()!=null){
				  Method.interactInventory(4480, "Use", "Cup of unfinished tea");
			  }else Inventory.selectItem(255);
		  }
		  
	  }
		
	}


	private void cs12() {
		final String opt[] = {"Hi there, the Gnome","Yep, it's a deal","A dwarf I know","Do you accept","Are you taking"};
		while(Widgets.get(1189).validate()){
			ChatOptions.getContinueOption().select(true);
		}
		if(new Tile(2915, 3438, 0).distanceTo()<10){
			Vars.DYNAMICV = false;
			initTile =null;
			if(!Method.findOption(opt)){
				if(!Method.isChatting("Sanfew")){
					Method.speakTo(454, "Sanfew");
				}
			}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToSanfew, "Walking to Sanfew", false);
		}else if(Vars.BURTHORPELODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(Vars.BURTHORPETELEPORT);
		
	}


	private void cs11() {
		final String opt[]  ={"I think I can manage","Have you always been","do something nice"};
		final int gangMembers[] = {1796,1797,1795};
		if(initTile!=null){
			if(loc1){
				if(new Tile(initTile.x()-51, initTile.getY()-39,0).distanceTo()<10){
					if(Method.isInCombat()){
						for(int g: gangMembers)
						Method.basicFightNPC(g);
						
					}else
					if(!Method.findOption(opt)){
						Vars.DYNAMICV = false;
						if(!Method.isChatting("Gangsta")){
							Method.speakTo(1794, "Hammer");
						}
					}
				}else new Tile(initTile.x()-51, initTile.getY()-39,0).clickOnMap();
			}else if(new Tile(initTile.x()+1, initTile.getY()-36,0).distanceTo()<10){
				loc1 = true;
			}else new Tile(initTile.x()+1, initTile.getY()-36,0).clickOnMap();
		}else if(SceneEntities.getNearest(31130)!=null){
			initTile = Players.getLocal().getLocation();
		}else
		if( new Tile(3019, 3448, 0).distanceTo()<7){
			Method.interactO(30942, "Climb", "Ladder");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToDwarvenMine, "Walking to Dwarven Mine", false);
		}else if(Vars.FALADORLODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(Vars.FALADORETELEPORT);
		
	}


	private void cs10() {
		final String opt[] = {"I'll deal"};
		if(Widgets.get(1189).validate()){
			ChatOptions.getContinueOption().select(true);
		}
		if(new Tile(3083, 3408, 0).distanceTo()<8){
		if(!Method.findOption(opt)){
			Vars.DYNAMICV = false;
			initTile =null;
			if(!Method.isChatting("Tassie")){
				Method.speakTo(1793, "Tassie");
			}
		}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToPotter, "Walking to Barbarian Village", false);
		}else if(Vars.VARROKLODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(Vars.VARROKTELEPORT);
		
	}


	private void cs9() {
		final String opt[] = {"Hey there! I","I guess it's not that","I need breathing salts","Talk about One"};
		while(Widgets.get(1186).validate() || Widgets.get(1189).validate()){
			ChatOptions.getContinueOption().select(true);
		}
		while(NPCs.getNearest(638)!=null && NPCs.getNearest(638).getAnimation()!=-1){
			Method.state("Waiting");
			Task.sleep(60);
		}
		
		if(new Tile(3190, 3404, 0).distanceTo()<8){
			if(new Tile(3194,3403,0).canReach()){
			if(!Method.findOption(opt)){
				Vars.DYNAMICV = false;
				if(!Method.isChatting("Apothecary")){
					if(!Widgets.get(1184).validate())
					Method.speakTo(638, "Apothecary");
				}
			}
			}else Method.interactO(24376, "Open", "Door");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToPotionShop, "Walking to the potion shop", false);
		}else if(Vars.VARROKLODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(Vars.VARROKTELEPORT);
		
	}



	private void cs8() {
		final String opt[]  ={"I have the","Ok, I guess one good","Hi, I need to talk"};
		if(!Method.teleporting&& Inventory.getItem(2353)==null && (Settings.get(2671)&0xFF) <=235){
			Method.bank(2353,3,false);
		}else
		if(new Tile(3229, 3437, 0).distanceTo()<10){
			while(Widgets.get(1189).validate()){
				ChatOptions.getContinueOption().select(true);
			}
		if(!Method.findOption(opt)){
			Vars.DYNAMICV = false;
			if(!Method.isChatting("Horvik")){
				Method.speakTo(549, "Horvik");
			}
		}
		}else if(Vars.DYNAMICV){
			Method.walking(pathToVarrokArmourShop, "Walking to Varrok armour shop", false);
		}else if(Vars.LUMMBRIDGELODE.distanceTo()<10 || Vars.VARROKLODE.distanceTo()<10){
			Vars.DYNAMICV  = true;
		}else if(Method.VarrokLodeIsActive()){
			Method.teleportTo(Vars.VARROKTELEPORT);
		}else Method.teleportTo(Vars.LUMBRIDGETELEPORT);
		
	}


	private void cs7() {
		NPC seth = NPCs.getNearest(452);
		final String opt[] = {"I guess it's not"};
		if(seth!=null && seth.getLocation().canReach()){
			if(seth.getLocation().distanceTo()<7){
				Vars.DYNAMICV = false;
				Method.skipPics();
				if(!Method.findOption(opt)){
					if(!Method.isChatting("Seth")){
						Method.speakTo(seth.getId(), "Seth");
					}
				}
			}else seth.getLocation().clickOnMap();
		}else
		if(new Tile(3237, 3293, 0) .distanceTo()<10){
		
			if(new Tile(3234,3295,0).canReach()){
				if(new Tile(3231,3291,0).distanceTo()<5){
					Method.interactO(45476, "Open", "Door");
				}else new Tile(3231,3291,0).clickOnMap();
			}else Method.interactO(45208, "Open", "Gate");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToSethTheFarmer, "Walking to Seth the farmer", false);
		}else if(Vars.LUMMBRIDGELODE.distanceTo()<10){
			Vars.DYNAMICV  =true;
		}else Method.teleportTo(Vars.LUMBRIDGETELEPORT);
		
	}


	private void cs6() {
		NPC fred = NPCs.getNearest(758);
		final String opt[] ={"I need to talk"};
		if(new Tile(3190,3273,0).canReach() && new Tile(3189,3276,0).distanceTo()<9){
			if(fred!=null){
				if(fred.getLocation().canReach()){
					if(fred.getLocation().distanceTo()<7){
						Vars.DYNAMICV = false;
						if(!Method.findOption(opt)){
							if(!Method.isChatting("Fred")){
								Method.speakTo(fred.getId(), "Fred");
							}
						}
					}else fred.getLocation().clickOnMap();
				}else Method.interactO(45476, "Open", "Door");
			}
		}else
		if(new Tile(3186, 3281, 0).distanceTo()<6){
		if(new Tile(3188,3278,0).canReach()){
			Method.interactO(45476, "Open", "Door");
		}else Method.interactO(45208, "Open", "Gate");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToFredTheFarmer, "Walking to Fred the farmer", false);
		}else if(Vars.DRAYNORLODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(Vars.DRAYNORTELEPORT);
		
	}


	private void cs5() {
		final String opt[]  ={"You're in luck","Ok, Jimmy","And I suppose","I'm looking for"};
		
		if(initTile!=null){
			if(new Tile(initTile.x()+24,initTile.getY()-33,0).canReach()){
				if(new Tile(initTile.x()+24,initTile.getY()-34,0).distanceTo()<10){
					Vars.DYNAMICV = false;
					if(!Method.findOption(opt)){
						if(!Method.isChatting("Johan")){
							Method.speakTo(1709, "Johan");
						}
					}
				}else new Tile(initTile.x()+24,initTile.getY()-34,0).clickOnMap();
			}else
			if(new Tile(initTile.x()+10,initTile.getY()-13,0).canReach()){
				if(new Tile(initTile.x()+24,initTile.getY()-33,0).distanceTo()<10){
					Method.interactO(1530, "Open", "Door");
				}else new Tile(initTile.x()+24,initTile.getY()-33,0).clickOnMap();
				
			}else if (new Tile(initTile.x()+8,initTile.getY()-10,0).distanceTo()<7){
				Method.interactO(1530, "Open", "Door");
			}else new Tile(initTile.x()+8,initTile.getY()-10,0).clickOnMap();
		}else if(SceneEntities.getNearest(31130)!=null){
			initTile = Players.getLocal().getLocation();
		}else
		if(new Tile(3164, 3250, 0).distanceTo()<7){
			if((Settings.get(114)>>14 &0x1) ==1){//This setting changes when the trap door opens.
				Method.interactO(5492, "Climb","Trapdoor");
			}else Method.interactO(5492, "Pick","Trapdoor");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToHam, "Walking to H.A.M headquarters",false);
		}else if(Vars.LUMMBRIDGELODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(Vars.LUMBRIDGETELEPORT);
		
	}


	private void cs4() {
		NPC Aggie = NPCs.getNearest(922);
		final String opt[] = {"Oh, Ok, I'll","Let me guess, you're","Talk about One Small"};
		if(new Tile(3088, 3259, 0).distanceTo()<10){
			if(new Tile(3087,3260,0).canReach()){//Inside aggies house
				if(Aggie.getLocation().distanceTo()<5){
					if(!Method.findOption(opt)){
						if(!Method.isChatting("Aggie")){
							Vars.DYNAMICV = false;
							Method.speakTo(Aggie.getId(), "Aggie");
						}
					}
				}else Aggie.getLocation().clickOnMap();
			}else Method.interactO(1239, "Open", "Door");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToAggie, "Walking to Aggie the witch", false);
		}else if(Vars.DRAYNORLODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else Method.teleportTo(Vars.DRAYNORTELEPORT);
		
	}


	private void cs3() {
		NPC Brain = NPCs.getNearest(559);
		final String opt[] = {"I've returned with","Ok, ok, I'll","Look, can you sharpen","Do you sharpen"};
		if(new Tile(3025, 3243, 0).distanceTo()<10){
			Method.skipPics();
			if(new Tile(3027,3246,0).canReach()){
				if(Brain!=null){
					if(Brain.getLocation().distanceTo()<7){
						if(!Method.findOption(opt)){
							Vars.DYNAMICV = false;
							if(!Method.isChatting("Brian")){
							Method.speakTo(559, "Brian");	
							}
						}
					}else Brain.getLocation().clickOnMap();
				}
			}else Method.interactO(40108, "Open", "Door");
		}else if(Vars.DYNAMICV){
			Method.walking(pathToPortSarimHatchetShop, "Walking to Port Sarim shop", false);
		}else if(Vars.DRAYNORLODE.distanceTo()<10 || Vars.PORTSARIMLODE.distanceTo()<10){
			Vars.DYNAMICV = true;
		}else if(Method.isPortSarimLodeAct()){
			Method.teleportTo(Vars.PORTSARIMTELEPORT);
		}else Method.teleportTo(Vars.DRAYNORTELEPORT);
		
	}


	private void cs2() {
		NPC forester = NPCs.getNearest(401);
		final String opt[] = {"Good news, I","Ok, I'll take your","I need to talk to you"};
		while(Widgets.get(1186).validate() || Widgets.get(1189).validate()){
			ChatOptions.getContinueOption().select(true);
		}
		Vars.DYNAMICV4 = false;
		if(new Tile(2861,2941,0).distanceTo()<12){
			if(forester!=null){
			if(forester.getLocation().distanceTo()<6){
				if(!Method.findOption(opt)){
					Vars.DYNAMICV = false;
					Vars.DYNAMICV2 = false;
					Vars.DYNAMICV3 = false;
					Vars.DYNAMICV4 = false;
					if(!Method.isChatting("Jungle forester")){
						Method.speakTo(401, "Jungle forester");
					}
				}
			}else forester.getLocation().clickOnMap();
			}
		}else cs1();
		
	}


	private void cs1() {
		final String opt[] = {"Yes"};//pay-fare
		final String opt2[]  ={"Here's the red","I'll get going then","Ok, see you in a ","You want me to do you","Is there anything"};
		
		while(Settings.get(1045)!=0){//Setting that changes when you start to sail
			Method.state("Sailing");
			Task.sleep(20);
		}
		while(SceneEntities.getNearest(2082)!=null){
			Method.state("Crossing plank");
			Method.interactO(2082, "Cross", "Plank");
		}
		 if(new Tile(2834,2984,0).distanceTo()<10){
			Vars.DYNAMICV4 = true;
		}
		if(Vars.DYNAMICV4 && (Settings.get(2671)&0x7) ==0||Vars.DYNAMICV4 && (Settings.get(2671)&0x1FF) ==275){
			if(new Tile(2834,2984,0).distanceTo()<10){
				Vars.DYNAMICV = false;
				Vars.DYNAMICV2 = false;
				Vars.DYNAMICV3 = false;
				Method.skipPics();
				if(Widgets.get(1184).validate()){
					speakTimer = new Timer(5000);
				}
				if(!Method.findOption(opt2)){
					if (!Method.isChatting("Yanni")){
						if(!speakTimer.isRunning() && !Widgets.get(1191).validate()){
						Method.speakTo(515, "Yanni");
						speakTimer = new Timer(8000);
						}
					}
				}
			}else Method.walking(pathToYanni, "Walking to Yanni", false);
		}else
		if(Vars.DYNAMICV3){
	       if(new Tile(2865,2952,0).canReach()){
	    	   Vars.DYNAMICV4 = true;
	       }else
			if(new Tile(2873,2952,0).canReach()){
				if(SceneEntities.getNearest(2262)!=null){
					if(SceneEntities.getNearest(2262).getLocation().distanceTo()<7){
						Method.interactO(2262, "Open", "Gate");
					}else SceneEntities.getNearest(2262).getLocation().clickOnMap();
				}
			}else
			if(new Tile(2876,2952,0).canReach()){
				Method.interactO(2259, "Open", "Metal gate");
			} else if (!Method.findOption(opt)) {
				if(Widgets.get(1186).validate()){
					ChatOptions.getContinueOption().select(true);
				}else Method.interactO(2216, "Climb", "Cart");
			}
			
			
		}else if(new Tile(2880,2952,0).distanceTo()<10){//Mosel Rei area.(outside the village)
			Vars.DYNAMICV3 = true;
		}else
		if(Vars.DYNAMICV2){
			if(SceneEntities.getNearest(24369)!=null && SceneEntities.getNearest(24369).isOnScreen()){
				Method.interactO(24369, "Open", "Gate");
			}else
			Method.walking(pathToMusaStart, "Walking to Mosel Rei's area", false);
		}else if(new Tile(2956,3146,0).canReach()){//If you can reach the Karamja port area
			Vars.DYNAMICV2 = true;
		}else
		if(new Tile(3028, 3215, 0).distanceTo()<10){//Port sarim(head to Karamja)
			if(!Method.findOption(opt)){
				if(!Method.isChatting("Officer")){
					Method.npcInteract(377, "Pay-fare");
				}
			}
		}else if(Vars.DYNAMICV){//Below ill get us to the port
			Method.walking(pathToPort, "Walking to Port Sarim", false);
		}else if(Vars.LUMMBRIDGELODE.distanceTo()<10|| Vars.DRAYNORLODE.distanceTo()<10||Vars.PORTSARIMLODE.distanceTo()<10){
			Vars.DYNAMICV  =true;
		}else if(Method.isPortSarimLodeAct()){
			Method.teleportTo(Vars.PORTSARIMTELEPORT);
		}else if(Method.DraynorLodeIsActive()){
			Method.teleportTo(Vars.DRAYNORTELEPORT);
		}else Method.teleportTo(Vars.LUMBRIDGETELEPORT);
		
	}

}
*/