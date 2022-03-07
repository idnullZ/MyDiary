package idnull.z.mydiary.ui.shared_component.animate

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry
import idnull.z.mydiary.ui.MainActivity
import idnull.z.mydiary.utils.Screen

@ExperimentalAnimationApi
fun enterAnimatedTransition(screen: Screen): AnimatedContentScope<String>.(
    initial: NavBackStackEntry,
    target: NavBackStackEntry
) -> EnterTransition? =
    { initial, _ ->
        when (initial.destination.route) {
           screen.route ->
                slideInHorizontally(
                    initialOffsetX = { 500 },
                    animationSpec = tween(MainActivity.ANIMATION_DURATION)
                ) + fadeIn(animationSpec = tween(MainActivity.ANIMATION_DURATION))
            else -> null
        }
    }


@ExperimentalAnimationApi
fun popEnterAnimatedTransition(screen: Screen): AnimatedContentScope<String>.(
    initial: NavBackStackEntry,
    target: NavBackStackEntry
) -> EnterTransition? =
    { initial, _ ->
        when (initial.destination.route) {
            screen.route ->
                slideInHorizontally(
                    initialOffsetX = { -500 },
                    animationSpec = tween(MainActivity.ANIMATION_DURATION)
                ) + fadeIn(animationSpec = tween(MainActivity.ANIMATION_DURATION))


            else -> null
        }
    }


@ExperimentalAnimationApi
fun exitAnimatedTransition(screen: Screen): AnimatedContentScope<String>.(
    initial: NavBackStackEntry,
    target: NavBackStackEntry
) -> ExitTransition? =
    { _, target ->
        when (target.destination.route) {
            screen.route ->
                slideOutHorizontally(
                    targetOffsetX = { -500 },
                    animationSpec = tween(MainActivity.ANIMATION_DURATION)
                ) + fadeOut(animationSpec = tween(MainActivity.ANIMATION_DURATION))

            else -> null
        }

    }

@ExperimentalAnimationApi
fun popExitAnimatedTransition(screen: Screen): AnimatedContentScope<String>.(
    initial: NavBackStackEntry,
    target: NavBackStackEntry
) -> ExitTransition? =
    { _, target ->
        when (target.destination.route) {
            screen.route ->

                slideOutHorizontally(
                    targetOffsetX = { 500 },
                    animationSpec = tween(MainActivity.ANIMATION_DURATION)

                ) + fadeOut(animationSpec = tween(MainActivity.ANIMATION_DURATION))

            else -> null
        }
    }


