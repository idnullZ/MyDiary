package idnull.z.mydiary.ui.shared_component.animate

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavBackStackEntry
import idnull.z.mydiary.ui.main.MainActivity.Companion.ANIMATION_DURATION
import idnull.z.mydiary.utils.Screen


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@ExperimentalFoundationApi
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
                    animationSpec = tween(ANIMATION_DURATION)
                ) + fadeIn(animationSpec = tween(ANIMATION_DURATION))
            else -> null
        }
    }


@ExperimentalComposeUiApi
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
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
                    animationSpec = tween(ANIMATION_DURATION)
                ) + fadeIn(animationSpec = tween(ANIMATION_DURATION))


            else -> null
        }
    }


@ExperimentalComposeUiApi
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
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
                    animationSpec = tween(ANIMATION_DURATION)
                ) + fadeOut(animationSpec = tween(ANIMATION_DURATION))

            else -> null
        }

    }

@ExperimentalComposeUiApi
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
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
                    animationSpec = tween(ANIMATION_DURATION)

                ) + fadeOut(animationSpec = tween(ANIMATION_DURATION))

            else -> null
        }
    }


