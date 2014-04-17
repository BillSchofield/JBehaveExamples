Scenario: Test addition using Tabular Parameters
When I add these numbers:
| x| y |
| 1| 2 |
| 2| 3 |
| 3|-4 |
Then I get these numbers:
|result|
|  3 |
|  5 |
| -1 |

Scenario: Test addition for kids using Parametrised Scenarios
Given <startingApples> apples
When I buy <moreApples> more apples
Then I have <totalApples> apples

Examples:
|startingApples|moreApples|totalApples|
|             1|        10|         11|
|            11|         1|         12|
|            21|        11|         32|


Scenario: Withdrawing money reduces balance
Given my balance is $100
When I withdraw $25
Then my balance is $75

Scenario: Spending money leaves less in my wallet
Given $100 in my wallet
When I spend $25
Then I have $75 left

Scenario: Multiple purchases are all removed from my wallet
Given $100 in my wallet
When I make purchases in the amounts: $10,$5,$15
Then my wallet contains $70

Scenario: Can't windraw money when balance is $0
Given I have withdrawn $100 from my initial balance of $100
When I withdraw $1
Then my balance is $0