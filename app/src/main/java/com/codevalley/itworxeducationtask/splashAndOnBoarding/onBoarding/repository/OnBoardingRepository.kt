package com.codevalley.itworxeducationtask.splashAndOnBoarding.onBoarding.repository


class OnBoardingRepository {
    var countriesIds: MutableList<String> = ArrayList()
    var countriesNames: MutableList<String> = ArrayList()
    var categoriesNames: MutableList<String> = ArrayList()


    fun fillCountries() {

        countriesIds.add(0, "0")
        countriesNames.add(0, "Choose your country")

        countriesIds.add(1, "ae")
        countriesNames.add(1, "United Arab Emirates")

        countriesIds.add(2, "ar")
        countriesNames.add(2, "Argentina")

        countriesIds.add(3, "at")
        countriesNames.add(3, "Austria")

        countriesIds.add(4, "au")
        countriesNames.add(4, "Australia")

        countriesIds.add(5, "be")
        countriesNames.add(5, "Belgium")

        countriesIds.add(6, "be")
        countriesNames.add(6, "Bulgaria")

        countriesIds.add(7, "br")
        countriesNames.add(7, "Brazil")

        countriesIds.add(8, "ca")
        countriesNames.add(8, "Canada")

        countriesIds.add(9, "ch")
        countriesNames.add(9, "Switzerland")

        countriesIds.add(10, "cn")
        countriesNames.add(10, "China")

        countriesIds.add(11, "co")
        countriesNames.add(11, "Colombia")

        countriesIds.add(12, "cu")
        countriesNames.add(12, "Cuba")

        countriesIds.add(13, "cz")
        countriesNames.add(13, "Czech Republic")

        countriesIds.add(14, "de")
        countriesNames.add(14, "Germany")

        countriesIds.add(15, "eg")
        countriesNames.add(15, "Egypt")

        countriesIds.add(16, "fr")
        countriesNames.add(16, "France")

        countriesIds.add(17, "gb")
        countriesNames.add(17, "United Kingdom")

        countriesIds.add(18, "gr")
        countriesNames.add(18, "Greece")

        countriesIds.add(19, "hk")
        countriesNames.add(19, "Hong Kong")

        countriesIds.add(20, "hu")
        countriesNames.add(20, "Hungary")

        countriesIds.add(21, "id")
        countriesNames.add(21, "Indonesia")

        countriesIds.add(22, "ie")
        countriesNames.add(22, "Ireland")

        countriesIds.add(23, "jp")
        countriesNames.add(23, "Japan")

        countriesIds.add(24, "kr")
        countriesNames.add(24, "South Korea")

        countriesIds.add(25, "lt")
        countriesNames.add(25, "Lithuania")

        countriesIds.add(26, "lv")
        countriesNames.add(26, "Latvia")

        countriesIds.add(27, "ma")
        countriesNames.add(27, "Morocco")

        countriesIds.add(28, "mx")
        countriesNames.add(28, "Mexico")

        countriesIds.add(29, "my")
        countriesNames.add(29, "Malaysia")

        countriesIds.add(30, "ng")
        countriesNames.add(30, "Nigeria")

        countriesIds.add(31, "nl")
        countriesNames.add(31, "Netherlands")

        countriesIds.add(32, "nz")
        countriesNames.add(32, "New Zealand")

        countriesIds.add(33, "ph")
        countriesNames.add(33, "Philippines")

        countriesIds.add(34, "pl")
        countriesNames.add(34, "Poland")

        countriesIds.add(35, "pt")
        countriesNames.add(35, "Portugal")

        countriesIds.add(36, "ro")
        countriesNames.add(36, "Romania")

        countriesIds.add(37, "rs")
        countriesNames.add(37, "Serbia")

        countriesIds.add(38, "ru")
        countriesNames.add(38, "Russia")

        countriesIds.add(39, "sa")
        countriesNames.add(39, "Saudi Arabia")

        countriesIds.add(40, "se")
        countriesNames.add(40, "Sweden")

        countriesIds.add(41, "sg")
        countriesNames.add(41, "Singapore")

        countriesIds.add(42, "si")
        countriesNames.add(42, "Slovenia")

        countriesIds.add(43, "sk")
        countriesNames.add(43, "Slovakia")

        countriesIds.add(44, "th")
        countriesNames.add(44, "Thailand")

        countriesIds.add(45, "tr")
        countriesNames.add(45, "Turkey")

        countriesIds.add(46, "tw")
        countriesNames.add(46, "Taiwan")

        countriesIds.add(47, "ua")
        countriesNames.add(47, "Ukraine")

        countriesIds.add(48, "us")
        countriesNames.add(48, "United States")

        countriesIds.add(49, "ve")
        countriesNames.add(49, "Venezuela")

        countriesIds.add(50, "za")
        countriesNames.add(50, "South Africa")
    }

    fun fillCategories() {
        categoriesNames.add(0, "business")
        categoriesNames.add(1, "entertainment")
        categoriesNames.add(2, "general")
        categoriesNames.add(3, "health")
        categoriesNames.add(4, "science")
        categoriesNames.add(5, "sports")
        categoriesNames.add(6, "technology")

    }
}