# FlexTab

<p align="center">
  <img src="https://img.shields.io/github/stars/seachal/FlexTab?style=social" alt="Github Stars">
  <img src="https://img.shields.io/github/forks/seachal/FlexTab?style=social" alt="Github Forks">
  <img src="https://img.shields.io/github/issues/seachal/FlexTab" alt="Github Issues">
  <img src="https://img.shields.io/github/license/seachal/FlexTab" alt="License">
</p>

<p align="center">
  <img src="screenshots/flextab_banner.png" width="80%" alt="FlexTab Banner">
</p>

<p align="center">
  <b>The Perfect Design-First Tab Control for Android</b>
</p>

<p align="center">
  <a href="#introduction">Introduction</a> •
  <a href="#features">Features</a> •
  <a href="#demo">Demo</a> •
  <a href="#installation">Installation</a> •
  <a href="#usage">Usage</a> •
  <a href="#customization">Customization</a> •
  <a href="#contributing">Contributing</a> •
  <a href="#license">License</a>
</p>

---

## Introduction

**FlexTab** is an Android tab control specifically designed to perfectly match design mockups. Have you ever struggled with complex tab indicator designs from your designers? Traditional tab components often fail to accurately reproduce the indicator effects in design mockups. FlexTab makes this simple!

> 💡 Perfect support for any image as an indicator without complex drawing code!

## Features

- ✨ **Design First** - Use image assets directly from design mockups as indicators
- 🎨 **Perfect Reproduction** - Accurately implement the designer's visual effects without complex custom drawing
- 🔄 **ViewPager2 Support** - Seamless integration with ViewPager2 for smooth page sliding
- 📐 **Flexible Layout** - Support for fixed width or adaptive width indicators
- 🖼️ **Image Indicators** - Easily use any image as an indicator, including irregular shapes
- 🎯 **Precise Positioning** - Smart calculations ensure precise indicator positioning
- 🚀 **High Performance** - Implemented based on RecyclerView for excellent scrolling performance
- 👆 **Smooth Animations** - Smooth indicator transition animations

## Demo

<p align="center">
  <img src="screenshots/demo_standard.gif" width="32%" alt="Standard Style">
  <img src="screenshots/demo_custom.gif" width="32%" alt="Custom Style">
  <img src="screenshots/demo_complex.gif" width="32%" alt="Complex Style">
</p>

## Installation

### Gradle

Add to your project level build.gradle:

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Add to your app level build.gradle:

```gradle
dependencies {
    implementation 'com.github.seachal:FlexTab:1.0.0'
}
```

## Usage

### Basic Usage

In your XML layout:

```xml
<com.seachal.flextab.CustomTabLayout
    android:id="@+id/customTabLayout"
    android:layout_width="match_parent"
    android:layout_height="48dp"
    android:background="#FFFFFF"
    app:o_selectedTextColor="#FF5722"
    app:o_normalTextColor="#666666" />
```

In your Activity or Fragment:

```java
// Set tab data
List<String> tabTitles = Arrays.asList("Tab 1", "Tab 2", "Tab 3");
customTabLayout.setTabItems(tabTitles);

// Set indicator image (directly use image from design)
customTabLayout.setIndicatorDrawable(
    ContextCompat.getDrawable(this, R.drawable.your_indicator_image));

// Set indicator size
customTabLayout.setIndicatorSize(
    getResources().getDimensionPixelSize(R.dimen.indicator_width),
    getResources().getDimensionPixelSize(R.dimen.indicator_height));

// Connect with ViewPager2
customTabLayout.setupWithViewPager2(viewPager);
```

### Integration with ViewPager2

```java
// Set up ViewPager2 adapter
viewPager.setAdapter(new FragmentStateAdapter(this) {
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
});

// Connect TabLayout with ViewPager2
customTabLayout.setupWithViewPager2(viewPager);
```

## Customization

### XML Attributes

| Attribute | Description | Default |
|-----------|-------------|---------|
| `o_indicatorDrawable` | Indicator drawable resource | None |
| `o_indicatorColor` | Indicator color (when using default drawing) | colorAccent |
| `o_indicatorHeight` | Indicator height | 4dp |
| `o_indicatorWidthMode` | Indicator width mode (0:fixed, 1:follow text) | 1 |
| `o_indicatorFixedWidth` | Fixed indicator width | 20dp |
| `o_indicatorRadius` | Indicator corner radius | 2dp |
| `o_selectedTextColor` | Selected text color | colorAccent |
| `o_normalTextColor` | Normal text color | #000000 |

### Custom Indicator Factory

If you need more complex indicator effects, you can implement a custom indicator factory:

```java
// Create custom indicator factory
customTabLayout.setIndicatorFactory(new TabIndicatorFactory() {
    @Override
    public TabIndicator createTabIndicator() {
        return new YourCustomIndicator();
    }
});

// Custom indicator implementation
class YourCustomIndicator implements TabIndicator {
    // Implement interface methods...
}
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request. Ensure your PR meets these requirements:

1. Code style is consistent with the project
2. New features include appropriate tests and documentation
3. Commit messages are clear and concise

## Change Log

**v1.0.0** (2024-05-30)
- Initial release
- Image indicator support
- ViewPager2 integration
- Custom indicator factory

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

```
MIT License

Copyright (c) 2024 Seachal

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
``` 