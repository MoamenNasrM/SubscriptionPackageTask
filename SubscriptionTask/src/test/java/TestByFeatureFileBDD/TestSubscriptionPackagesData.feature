  Feature: Test Subscription Packages Data

    Scenario Outline: [Test End to end Scenario - Verify all Subscription Packages Data on system Vs. excel sheet]
      Given user navigate to stc website by <Country>
      When the page is opened successfully
      Then the subscription packages should be displayed with its details for <Country> like expected data in excel file

      Examples:
        | Country      |
        | Saudi Arabia |
        | Bahrain      |
        | Kuwait       |


    Scenario Outline: [Test End to end Scenario - Verify all Subscription Packages Data on system Vs. feature file data]
      Given user navigate to stc website by <CountryName>
      When the page is opened successfully
      Then Lite subscription package should be displayed with its details
        | Price          | Currency          |
        | <LitePrice>    | <PackageCurrency> |
      And Classic subscription package should be displayed with its details
        | Price          | Currency          |
        | <ClassicPrice> | <PackageCurrency> |
      And Premium subscription package should be displayed with its details
        | Price          | Currency          |
        | <PremiumPrice> | <PackageCurrency> |

      Examples:
      | CountryName  | LitePrice | ClassicPrice | PremiumPrice | PackageCurrency |
      | Saudi Arabia | 15        | 25           | 60           | SAR/month       |
      | Bahrain      |  2        | 3            | 6            | BHD/month       |
      | Kuwait       |  1.2      | 2.5          | 4.8          | KWD/month       |
